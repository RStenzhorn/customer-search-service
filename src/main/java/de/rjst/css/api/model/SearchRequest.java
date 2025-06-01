package de.rjst.css.api.model;

import de.rjst.css.api.SearchType;

public interface SearchRequest {
    String getFirstName();
    String getLastName();
    String getEmail();
    String getPhone();
    SearchType getSearchType();
}
