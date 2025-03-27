package org.wildfly.camel.examples.cdi.service;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

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
		if (areEqual(postgresSubscription.getIsDeleted(), mssqlSubscription.getIsActive())
				&& areEqual(postgresSubscription.getRenewSubscriptionFlag(), mssqlSubscription.getIsRenewal())
				&& areEqual(postgresSubscription.getBundleId(), mssqlSubscription.getPackageId().longValue())
				&& areEqual(postgresSubscription.getStartDate(), mssqlSubscription.getStartDate())
				&& areEqual(postgresSubscription.getEndDate(), mssqlSubscription.getEndDate())
				&& Math.abs(postgresSubscription.getPaidAmount() - mssqlSubscription.getPrice().floatValue()) < 0.0001) {
			return false;
		}
		System.out.println("These two objects are different in some attributes: \npostgresObj = " + postgresSubscription
				+ "\nmssqlObj = " + mssqlSubscription);
		
		System.out.println("\n==========================================================\n"
				+ "Comparing objects::\n"
				+ "IsDeleted = " + areEqual(postgresSubscription.getIsDeleted(), mssqlSubscription.getIsActive())
				+ "\nRenewSubscriptionFlag = " + areEqual(postgresSubscription.getRenewSubscriptionFlag(), mssqlSubscription.getIsRenewal())
				+ "\nBundleId = " + areEqual(postgresSubscription.getBundleId(), mssqlSubscription.getPackageId().longValue()) 
				+ "\nStartDate = " + areEqual(postgresSubscription.getStartDate(), mssqlSubscription.getStartDate())
				+ "\nEndDate = " + areEqual(postgresSubscription.getEndDate(), mssqlSubscription.getEndDate())
				+ "\nPaidAmount = " + (Math.abs(postgresSubscription.getPaidAmount() - mssqlSubscription.getPrice().floatValue()) < 0.0001)
			);
		return true;
	}
	
	public static Boolean isDifferentVehicle(Vehicle vehicle, Account account, Person person, Subscriber subscriber) throws IllegalArgumentException {
		if((vehicle != null && subscriber == null) || (vehicle == null && subscriber != null)) {
			return true;
		}
		if(vehicle == null && subscriber == null) {
			throw new IllegalArgumentException("Both entities are null!!!");
		}
		if (
				areEqual(vehicle.getVehicleTypeId(), subscriber.getVehicleTypeId())
				&& areEqual(vehicle.getTagValue(), subscriber.getTagValue())
				&& areEqual(subscriber.getCarPlateNo(), processPlateNumbersAndLetters(vehicle.getPlateNumbers(), vehicle.getPlateLetters())) 
				&& areEqual(account.getIsForeigner(), subscriber.getIsForeigner())
				&& areEqual(account.getUserId(), subscriber.getUserId())
				&& areEqual(person.getName(), subscriber.getName())
				&& areEqual(person.getMobileNumber(), subscriber.getPhoneNumber())
				&& areEqual(person.getNationalNumber(), subscriber.getNationalId())
			) {
			return false;
		}
		System.out.println("These objects are different in some attributes: \n" + vehicle 
				+ "\n" + account
				+ "\n" + person
				+ "\nmssqlObj = " + subscriber);
		
		System.out.println("\n==========================================================\n"
				+ "Comparing objects::\n"
				+ "VehicleTypeId = " + areEqual(vehicle.getVehicleTypeId(), subscriber.getVehicleTypeId())
				+ "\nTagValue = " + areEqual(vehicle.getTagValue(), subscriber.getTagValue())
				+ "\nCarPlateNo = " + areEqual(subscriber.getCarPlateNo(), processPlateNumbersAndLetters(vehicle.getPlateNumbers(), vehicle.getPlateLetters())) 
				+ "\nIsForeigner = " + areEqual(account.getIsForeigner(), subscriber.getIsForeigner())
				+ "\nUserId = " + areEqual(account.getUserId(), subscriber.getUserId())
				+ "\nName = " + areEqual(person.getName(), subscriber.getName())
				+ "\nMobileNumber = " + areEqual(person.getMobileNumber(), subscriber.getPhoneNumber())
				+ "\nNationalNumber = " + areEqual(person.getNationalNumber(), subscriber.getNationalId())
				);
		
//		try (BufferedWriter writer = new BufferedWriter(
//                new OutputStreamWriter(
//                    new FileOutputStream("E:\\Egabi_Workspace\\ITS_FUES_DB_REPLICATION\\logs\\object_differences.log", true), 
//                    StandardCharsets.UTF_8))) {
//            String differenceMessage = "These objects are different in some attributes: \n" + 
//                vehicle + "\n" + 
//                account + "\n" + 
//                person + "\n" + 
//                "mssqlObj = " + subscriber;
//            
//            // Write to file
//            writer.write(differenceMessage);
//            writer.newLine();
//            writer.write("-----------------------------");
//            writer.newLine();
//            
//            // Also print to console
//            System.out.println(differenceMessage);
//        } catch (IOException e) {
//            System.err.println("Error writing to log file: " + e.getMessage());
//        }
		return true;
	}
	
	private static <T> boolean areEqual(T obj1, T obj2) {
	    if (obj1 == null && obj2 == null) {
	        return true;
	    }
	    
	    if (obj1 == null || obj2 == null) {
	        return false;
	    }
	    
	    return obj1.equals(obj2);
	}
	
	public static String processPlateNumbersAndLetters(String plateNumbers, String plateLetters) {
		if(plateNumbers != null && plateLetters != null) {
			return plateLetters + " / " + plateNumbers;
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
