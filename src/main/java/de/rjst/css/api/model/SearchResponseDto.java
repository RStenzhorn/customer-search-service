package de.rjst.css.api.model;

import de.rjst.css.database.CustomerEntity;
import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class SearchResponseDto {
    private CustomerEntity customer;
    private Map<String, List<String>> highlightedFields;
    private Float score;
}

