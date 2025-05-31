package de.rjst.css.database;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum FieldNames {

    FIRSTNAME("firstName"),
    LASTNAME("lastName"),
    EMAIL("email"),
    PHONE("phone"),
    IP_ADDRESS("ipAddress"),
    CUSTOMER_ID("customerId");


    public final String name;

}
