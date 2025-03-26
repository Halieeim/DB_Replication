package org.wildfly.camel.examples.cdi.service;

import org.wildfly.camel.examples.cdi.entity.Account;
import org.wildfly.camel.examples.cdi.entity.MssqlSubscription;
import org.wildfly.camel.examples.cdi.entity.Person;
import org.wildfly.camel.examples.cdi.entity.PostgresSubscription;
import org.wildfly.camel.examples.cdi.entity.Subscriber;
import org.wildfly.camel.examples.cdi.entity.Vehicle;

public class ComparatorService {
	public static Boolean isDifferentSubscription(PostgresSubscription postgresSubscription,
			MssqlSubscription mssqlSubscription) throws IllegalArgumentException {
		if((postgresSubscription != null && mssqlSubscription == null) || (postgresSubscription == null && mssqlSubscription != null)) {
			return true;
		}
		if(postgresSubscription == null && mssqlSubscription == null) {
			throw new IllegalArgumentException("Both entities are null!!!");
		}
		if (postgresSubscription.getIsDeleted() == mssqlSubscription.getIsActive()
				&& postgresSubscription.getRenewSubscriptionFlag() == mssqlSubscription.getIsRenewal()
				&& postgresSubscription.getBundleId() == mssqlSubscription.getPackageId().longValue()
				&& postgresSubscription.getStartDate().equals(mssqlSubscription.getStartDate())
				&& postgresSubscription.getEndDate().equals(mssqlSubscription.getEndDate())
				&& Math.abs(postgresSubscription.getPaidAmount() - mssqlSubscription.getPrice().floatValue()) < 0.0001) {
			return false;
		}
		System.out.println("These two objects are different in some attributes: \npostgresObj = " + postgresSubscription
				+ "\nmssqlObj = " + mssqlSubscription);
		return true;
	}
	
	public static Boolean isDifferentVehicle(Vehicle vehicle, Account account, Person person, Subscriber subscriber) throws IllegalArgumentException {
		if((vehicle != null && subscriber == null) || (vehicle == null && subscriber != null)) {
			return true;
		}
		if(vehicle == null && subscriber == null) {
			throw new IllegalArgumentException("Both entities are null!!!");
		}
		if (vehicle.getVehicleTypeId() == subscriber.getVehicleTypeId()
				&& stringsAreTheSame(vehicle.getTagValue(), subscriber.getTagValue())
				&& stringsAreTheSame(subscriber.getCarPlateNo(), processPlateNumbersAndLetters(vehicle.getPlateNumbers(), vehicle.getPlateLetters())) 
				&& account.getIsForeigner() == subscriber.getIsForeigner()
				&& account.getUserId() == subscriber.getUserId()
				&& stringsAreTheSame(person.getName(), subscriber.getName())
				&& stringsAreTheSame(person.getMobileNumber(), subscriber.getPhoneNumber())
				&& stringsAreTheSame(person.getNationalNumber(), subscriber.getNationalId())
			) {
			return false;
		}
		System.out.println("These objects are different in some attributes: \n" + vehicle 
				+ "\n" + account
				+ "\n" + person
				+ "\nmssqlObj = " + subscriber);
		return true;
	}
	
	private static Boolean stringsAreTheSame(String str1, String str2) {
		if(str1 == null && str2 == null) {
			return true;
		}
		if(str1 == null || str2 == null) {
			return true;
		}
		return str1.equals(str2);
	}
	
	public static String processPlateNumbersAndLetters(String plateNumbers, String plateLetters) {
		if(plateNumbers != null && plateLetters != null) {
			return plateNumbers + " / " + plateLetters;
		}
		if(plateNumbers == null && plateLetters == null) {
			return null;
		}
		if(plateNumbers == null) {
			return plateLetters;
		}
		return plateNumbers;
	}
}
