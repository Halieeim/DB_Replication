package org.wildfly.camel.examples.cdi;

import java.io.PrintStream;
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
		
		generalValidation(vecList, Subscriber.class.getName(), postgresEntityManager, mssqlEntityManager);

		postgresEntityManager.close();
		postgresEntityManagerFactory.close();

		mssqlEntityManager.close();
        mssqlEntityManagerFactory.close();
        
		String result = vecList.get(0).toString() + "\nresult set size for vehicles = " + vecList.size() + "\n"
				+ postgresSubscriptions.get(0) + "\nresult set size for postgres subscriptions = "
				+ postgresSubscriptions.size();
		return result;
	}

	private <T> void generalValidation(List<T> postgresObjects, String mssqlEntityName, EntityManager postgresEntityManager, EntityManager mssqlEntityManager) {
		long counter = 0;
		long iterator = 0;
		for (T postgresObj : postgresObjects) {
//			System.out.println("PostgresObj:: " + postgresObj);
			System.out.println("Iteration " + ++iterator);
			Long id;
			try {
				id = (Long) postgresObj.getClass().getMethod("getId").invoke(postgresObj);
			} catch (Exception e) {
				throw new IllegalArgumentException(
						"Object of type " + postgresObj.getClass().getName() + " does not have a getId() method", e);
			}

			// find the corresponding object by the id
			T obj = null;
			if (mssqlEntityName.equalsIgnoreCase(Subscriber.class.getName())) {
				obj = (T) mssqlEntityManager.find(Subscriber.class, id);
			} else if (mssqlEntityName.equalsIgnoreCase(MssqlSubscription.class.getName())) {
				obj = (T) mssqlEntityManager.find(MssqlSubscription.class, id);
			}

			Account account = null;
			Person person = null;
			
			EntityTransaction transaction = mssqlEntityManager.getTransaction();
			try {
				// retrieving needed data in case of replicating vehicles/subscribers
				if(mssqlEntityName.equalsIgnoreCase(Subscriber.class.getName())) {
					Long accountId = ((Vehicle)postgresObj).getAccountId();
					person = postgresEntityManager.find(Person.class, accountId);
					account = postgresEntityManager.find(Account.class, accountId);
					if(person == null) {
						throw new EntityNotFoundException("Could not find Person entity with account id = " + accountId);
					}
					if(account == null) {
						throw new EntityNotFoundException("Could not find Account entity with id = " + accountId);
					}
				    System.out.println("Account found: " + account);
				    System.out.println("Person found:: " + person);
				}
				transaction.begin();
				// if object not found, construct the object for RFID db and insert it.
				if (obj == null) {
					// construct the object
					if (mssqlEntityName.equalsIgnoreCase(Subscriber.class.getName())) {
						Subscriber subscriber = VehicleMapper.mapToSubscriber((Vehicle)postgresObj, account, person);
						mssqlEntityManager.persist(subscriber);
						transaction.commit();
						counter++;
						System.out.println("Inserted new subscriber obj in RFID: " + subscriber);
					} else if (mssqlEntityName.equalsIgnoreCase(MssqlSubscription.class.getName())) {
						MssqlSubscription subscription = SubscriptionMapper.toMssql((PostgresSubscription) postgresObj);
						mssqlEntityManager.persist(subscription);
						transaction.commit();
						counter++;
						System.out.println("Inserted new subscription obj in RFID: " + subscription);
					}
				} else {
					// Check differences between the cspdb object and the RFID object. If there is any differences update the object in RFID.
					if (mssqlEntityName.equalsIgnoreCase(Subscriber.class.getName())) {
						Boolean isDifferent = ComparatorService.isDifferentVehicle((Vehicle)postgresObj, account, person, (Subscriber)obj);
						if (isDifferent) {
							Subscriber subscriber = VehicleMapper.mapToSubscriber((Vehicle)postgresObj, account, person);
							mssqlEntityManager.merge(subscriber);
							transaction.commit();
							counter++;
						}
					} else if (mssqlEntityName.equalsIgnoreCase(MssqlSubscription.class.getName())) {
						Boolean isDifferent = ComparatorService.isDifferentSubscription((PostgresSubscription) postgresObj, (MssqlSubscription) obj);
						if(isDifferent) {
							MssqlSubscription subscription = SubscriptionMapper.toMssql((PostgresSubscription) postgresObj);
							mssqlEntityManager.merge(subscription);
							transaction.commit();
							counter++;
						}
					}
				}
			} catch (Exception e) {
				System.err.println("Rolling back...");
				if (transaction.isActive()) {
					transaction.rollback();
				}
				e.printStackTrace();
			}
		}
		System.out.println("No. added/updated records in the RFID db = " + counter);
	}
}
