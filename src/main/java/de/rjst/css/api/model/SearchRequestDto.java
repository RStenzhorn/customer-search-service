package de.rjst.css.api.model;

import lombok.Data;

@Data
public class SearchRequestDto {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String ipAddress;

}
