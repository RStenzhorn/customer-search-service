package de.rjst.css.logic;

import de.rjst.css.api.Explanation;
import de.rjst.css.database.FieldNames;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import org.springframework.stereotype.Component;

@Component
public class MatchedFieldMapper implements Function<Explanation, List<String>> {

    @Override
    public List<String> apply(final Explanation explanation) {
        final List<String> result = new ArrayList<>();

        final List<Explanation> details = explanation.getDetails();
        if (details != null) {
            for (final var detail : details) {
                final var description = detail.getDescription();
                getMatchedFields(description, result);
            }
        }
        return result;
    }

    private void getMatchedFields(final String details, final List<String> matchedFields) {
        for (final FieldNames value : FieldNames.values()) {
            final var name = value.name;
            if (details.contains(name)) {
                matchedFields.add(name);
            }
        }
    }

}
