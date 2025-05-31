package de.rjst.css.api.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import de.rjst.css.api.Explanation;
import de.rjst.css.database.CustomerEntity;
import lombok.Data;
import lombok.SneakyThrows;

@Data
public class ElasticResponseDto {

    private CustomerEntity entity;
    private Float score;
    private Explanation explanation;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public ElasticResponseDto(final CustomerEntity entity, final Float score, final JsonObject explanationJson) {
        this.entity = entity;
        this.score = score;
        final JsonNode jsonNode = objectMapper.readTree(explanationJson.toString());
        explanation = objectMapper.treeToValue(jsonNode, Explanation.class);
    }


}

