package de.rjst.css.api;

import java.util.List;
import lombok.Data;

@Data
public class Explanation {
    private Float value;
    private String description;
    private List<Explanation> details;
}
