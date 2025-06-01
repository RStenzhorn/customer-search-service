package de.rjst.css.api.model;

import de.rjst.css.api.SearchType;
import lombok.Data;

@Data
public class SearchRequestDto implements SearchRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private SearchType searchType;


}
