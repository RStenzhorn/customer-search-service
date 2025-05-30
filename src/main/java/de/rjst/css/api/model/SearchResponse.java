package de.rjst.css.api.model;

import de.rjst.css.database.CustomerEntity;
import java.util.List;
import lombok.Data;

@Data
public class SearchResponse {

    private List<String> matchedFields;
    private CustomerEntity entity;
    private Float score;

}
