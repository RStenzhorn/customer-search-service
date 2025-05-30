package de.rjst.css.api.model;

import de.rjst.css.database.CustomerEntity;
import java.util.Set;
import lombok.Data;

@Data
public class SearchResponse {

    private Set<String> matchedFields;
    private CustomerEntity entity;
    private Float score;

}
