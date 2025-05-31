package de.rjst.css.logic;

import de.rjst.css.api.Explanation;
import de.rjst.css.database.FieldNames;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import org.springframework.stereotype.Component;

@Component
public class MatchedFieldMapper implements Function<Explanation, Set<String>> {

    @Override
    public Set<String> apply(final Explanation explanation) {
        final Set<String> result = new HashSet<>();

        final List<Explanation> details = explanation.getDetails();
        if (details != null) {
            for (final var detail : details) {
                final var description = detail.getDescription();
                getMatchedFields(description, result);
            }
        }
        return result;
    }

    private static void getMatchedFields(final String details, final Collection<String> matchedFields) {
        for (final FieldNames value : FieldNames.values()) {
            final var name = value.name;
            if (details.contains(name)) {
                matchedFields.add(name);
            }
        }
    }

}
