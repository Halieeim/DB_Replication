package org.wildfly.camel.examples.cdi.entity.mapper;

import org.wildfly.camel.examples.cdi.entity.MssqlSubscription;
import org.wildfly.camel.examples.cdi.entity.PostgresSubscription;

import java.sql.Timestamp;

public class SubscriptionMapper {
	public static MssqlSubscription toMssql(PostgresSubscription postgresSubscription) {
        if (postgresSubscription == null) {
            return null;
        }
        MssqlSubscription mssqlSubscription = new MssqlSubscription();
        mssqlSubscription.setId(postgresSubscription.getId());
        mssqlSubscription.setSubscriberId(postgresSubscription.getVehicleId().intValue());
        mssqlSubscription.setStartDate(postgresSubscription.getStartDate());
        mssqlSubscription.setEndDate(postgresSubscription.getEndDate());
        mssqlSubscription.setPrice(postgresSubscription.getPaidAmount().doubleValue());
        mssqlSubscription.setDiscount(postgresSubscription.getDiscount() != null ? postgresSubscription.getDiscount().doubleValue() : 0.0);
        mssqlSubscription.setCreatedAt(Timestamp.valueOf(postgresSubscription.getCreatedOn()));
        mssqlSubscription.setUpdatedAt(Timestamp.valueOf(postgresSubscription.getUpdatedOn()));
        mssqlSubscription.setBillNumber(Integer.parseInt(postgresSubscription.getReceiptNumber()));
        mssqlSubscription.setIsActive(postgresSubscription.getIsDeleted());
        mssqlSubscription.setIsRenewal(postgresSubscription.getRenewSubscriptionFlag());
        mssqlSubscription.setPackageId(postgresSubscription.getBundleId().intValue());
        
        mssqlSubscription.setDiscountIsPercentage(true);
        mssqlSubscription.setPaymentTypeId(-1);
        mssqlSubscription.setUserId(-1);
        return mssqlSubscription;
    }
}
