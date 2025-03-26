package org.wildfly.camel.examples.cdi.entity.mapper;

import java.sql.Timestamp;

import javax.persistence.Column;

import org.wildfly.camel.examples.cdi.entity.Account;
import org.wildfly.camel.examples.cdi.entity.Person;
import org.wildfly.camel.examples.cdi.entity.Subscriber;
import org.wildfly.camel.examples.cdi.entity.Vehicle;
import org.wildfly.camel.examples.cdi.service.ComparatorService;

public class VehicleMapper {

	public static Subscriber mapToSubscriber(Vehicle vehicle, Account account, Person person) {
        if (vehicle == null) {
            return null;
        }

        Subscriber subscriber = new Subscriber();
        subscriber.setId(vehicle.getId());
        subscriber.setVehicleTypeId(vehicle.getVehicleTypeId());
        subscriber.setTagValue(vehicle.getTagValue());
        subscriber.setCarPlateNo(ComparatorService.processPlateNumbersAndLetters(vehicle.getPlateNumbers(), vehicle.getPlateLetters()));
        
        subscriber.setName(person.getName());
        subscriber.setNationalId(person.getNationalNumber());
        subscriber.setPhoneNumber(person.getMobileNumber());
        subscriber.setStatus(2);							//<==================
        subscriber.setUserId(account.getUserId());
        subscriber.setCreatedAt(Timestamp.valueOf(vehicle.getCreatedOn()));
        subscriber.setUpdatedAt(Timestamp.valueOf(vehicle.getUpdatedOn()));
        subscriber.setIsForeigner(account.getIsForeigner());
        subscriber.setIsCarPlateNoHasBeenReused(false);			//<==================

        return subscriber;
    }
}
