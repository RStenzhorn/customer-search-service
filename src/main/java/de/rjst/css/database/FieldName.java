package de.rjst.css.database;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum FieldName {

    FIRSTNAME("firstName"),
    LASTNAME("lastName"),
    EMAIL("email"),
    PHONE("phone");


    public final String name;

}
