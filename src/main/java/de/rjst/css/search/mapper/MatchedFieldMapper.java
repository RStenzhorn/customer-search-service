package de.rjst.css.search.mapper;

import de.rjst.css.api.Explanation;
import de.rjst.css.database.FieldName;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class MatchedFieldMapper implements Function<Explanation, Set<String>> {

    @Override
    public Set<String> apply(final Explanation explanation) {
        Set<String> result = new HashSet<>();

        if (explanation != null) {
            final List<Explanation> details = explanation.getDetails();

            if (details != null) {
                result = details.stream()
                                .filter(Objects::nonNull)
                                .map(Explanation::getDescription)
                                .filter(Objects::nonNull)
                                .flatMap(description -> getMatchedFields(description).stream())
                                .collect(Collectors.toSet());
            }
        }

        return result;
    }

    private static Set<String> getMatchedFields(final String description) {
        return Arrays.stream(FieldName.values())
                     .map(fieldName -> fieldName.name)
                     .filter(description::contains)
                     .collect(Collectors.toSet());
    }
}
