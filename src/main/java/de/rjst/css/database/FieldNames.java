package de.rjst.css.database;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
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

    public static final Map<String, FieldNames> BY_NAME =
        Arrays.stream(values())
              .collect(Collectors.toMap(e -> e.name, e -> e));
}
