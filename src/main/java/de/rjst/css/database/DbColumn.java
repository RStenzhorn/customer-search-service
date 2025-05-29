package de.rjst.css.database;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum DbColumn {

    FIRSTNAME("firstname"),
    LASTNAME("lastname"),
    EMAIL("email"),
    PHONE("phone"),
    IP_ADDRESS("ip_address"),
    CUSTOMER_ID("customer_id");


    public final String name;

}
