package org.wildfly.camel.examples.cdi;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.wildfly.camel.examples.cdi.entity.Account;
import org.wildfly.camel.examples.cdi.entity.MssqlSubscription;
import org.wildfly.camel.examples.cdi.entity.Person;
import org.wildfly.camel.examples.cdi.entity.PostgresSubscription;
import org.wildfly.camel.examples.cdi.entity.Subscriber;
import org.wildfly.camel.examples.cdi.entity.Vehicle;
import org.wildfly.camel.examples.cdi.entity.mapper.SubscriptionMapper;
import org.wildfly.camel.examples.cdi.entity.mapper.VehicleMapper;
import org.wildfly.camel.examples.cdi.service.ComparatorService;

public class ReplicationValidation {
	private final int NUMBER_OF_DAYS = 30;
	
	public String validateReplication() throws Exception {
		System.out.println("Entered SomeMethod");
		EntityManagerFactory postgresEntityManagerFactory = Persistence.createEntityManagerFactory("camel");
		EntityManager postgresEntityManager = postgresEntityManagerFactory.createEntityManager();

		List<Vehicle> vecList = postgresEntityManager
				.createQuery("SELECT v FROM Vehicle v where v.updatedOn > current_date() - :days")
				.setParameter("days", NUMBER_OF_DAYS).getResultList();

		List<PostgresSubscription> postgresSubscriptions = postgresEntityManager
				.createQuery("SELECT s FROM PostgresSubscription s where s.updatedOn > current_date() - :days")
				.setParameter("days", NUMBER_OF_DAYS).getResultList();
		
		EntityManagerFactory mssqlEntityManagerFactory = Persistence.createEntityManagerFactory("mssql");
        EntityManager mssqlEntityManager = mssqlEntityManagerFactory.createEntityManager();
		
		validate(vecList, Subscriber.class.getName(), postgresEntityManager, mssqlEntityManager);
		validate(postgresSubscriptions, MssqlSubscription.class.getName(), postgresEntityManager, mssqlEntityManager);

		postgresEntityManager.close();
		postgresEntityManagerFactory.close();

		mssqlEntityManager.close();
        mssqlEntityManagerFactory.close();
        
		String result = vecList.get(0).toString() + "\nresult set size for vehicles = " + vecList.size() + "\n"
				+ postgresSubscriptions.get(0) + "\nresult set size for postgres subscriptions = "
				+ postgresSubscriptions.size();
		return result;
	}

	private <T> void validate(List<T> sourceObjects, String targetEntityName, 
			EntityManager sourceEntityManager,EntityManager targetEntityManager) {
		long counter = 0;
		long iterator = 0;
		for (T sourceObj : sourceObjects) {
			System.out.println("Iteration " + ++iterator);
			Long id;
			try {
				id = (Long) sourceObj.getClass().getMethod("getId").invoke(sourceObj);
			} catch (Exception e) {
				throw new IllegalArgumentException(
						"Object of type " + sourceObj.getClass().getName() + " does not have a getId() method", e);
			}

			// find the corresponding object by the id
			T obj = getCorrespondingObject(targetEntityName, targetEntityManager, id);

			EntityTransaction transaction = targetEntityManager.getTransaction();
			try {
				replicateData(obj, sourceObj, targetEntityName, sourceEntityManager, targetEntityManager, transaction, counter);
			} catch (Exception e) {
				System.err.println("Rolling back...");
				e.printStackTrace();
			} finally {
				if (transaction.isActive()) {
					transaction.rollback();
				}
			}
		}
		System.out.println("No. added/updated records in the RFID db = " + counter);
	}
	
	private <T> T getCorrespondingObject(String targetEntityName, EntityManager targetEntityManager, Long id){
		if (targetEntityName.equalsIgnoreCase(Subscriber.class.getName())) {
			return (T) targetEntityManager.find(Subscriber.class, id);
		}
		if (targetEntityName.equalsIgnoreCase(MssqlSubscription.class.getName())) {
			return (T) targetEntityManager.find(MssqlSubscription.class, id);
		}
		return null;
	}
	
	private <T> void replicateData(T obj, T sourceObj, String targetEntityName, EntityManager sourceEntityManager,
			EntityManager targetEntityManager, EntityTransaction transaction, long counter) throws Exception {
		Account account = null;
		Person person = null;
		// retrieving needed data in case of replicating vehicles/subscribers
		if(targetEntityName.equalsIgnoreCase(Subscriber.class.getName())) {
			Long accountId = ((Vehicle)sourceObj).getAccountId();
			person = sourceEntityManager.find(Person.class, accountId);
			account = sourceEntityManager.find(Account.class, accountId);
			if(person == null) {
				throw new EntityNotFoundException("Could not find Person entity with account id = " + accountId);
			}
			if(account == null) {
				throw new EntityNotFoundException("Could not find Account entity with id = " + accountId);
			}
		}
		transaction.begin();
		// if object not found, construct the object for RFID db and insert it.
		if (obj == null) {
			// construct the object
			if (targetEntityName.equalsIgnoreCase(Subscriber.class.getName())) {
				Subscriber subscriber = VehicleMapper.mapToSubscriber((Vehicle)sourceObj, account, person);
				targetEntityManager.persist(subscriber);
				transaction.commit();
				counter++;
				System.out.println("Inserted new subscriber obj in RFID: " + subscriber);
			} else if (targetEntityName.equalsIgnoreCase(MssqlSubscription.class.getName())) {
				MssqlSubscription subscription = SubscriptionMapper.toMssql((PostgresSubscription) sourceObj);
				targetEntityManager.persist(subscription);
				transaction.commit();
				counter++;
				System.out.println("Inserted new subscription obj in RFID: " + subscription);
			} else {
				throw new Exception("targetEntityName IS NOT VALID!!!");
			}
		} else {
			// Check differences between the cspdb object and the RFID object. If there is any differences update the object in RFID.
			if (targetEntityName.equalsIgnoreCase(Subscriber.class.getName())) {
				Boolean isDifferent = ComparatorService.isDifferentVehicle((Vehicle)sourceObj, account, person, (Subscriber)obj);
				if (isDifferent) {
					Subscriber subscriber = VehicleMapper.mapToSubscriber((Vehicle)sourceObj, account, person);
					targetEntityManager.merge(subscriber);
					transaction.commit();
					counter++;
				}
			} else if (targetEntityName.equalsIgnoreCase(MssqlSubscription.class.getName())) {
				Boolean isDifferent = ComparatorService.isDifferentSubscription((PostgresSubscription) sourceObj, (MssqlSubscription) obj);
				if(isDifferent) {
					MssqlSubscription subscription = SubscriptionMapper.toMssql((PostgresSubscription) sourceObj);
					targetEntityManager.merge(subscription);
					transaction.commit();
					counter++;
				}
			} else {
				throw new Exception("targetEntityName IS NOT VALID!!!");
			}
		}
	}
}
