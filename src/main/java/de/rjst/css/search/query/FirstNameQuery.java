package de.rjst.css.search.query;

import de.rjst.css.api.SearchType;
import de.rjst.css.api.model.SearchRequest;
import de.rjst.css.database.FieldName;
import java.util.function.BiConsumer;
import java.util.function.Function;
import org.hibernate.search.engine.search.predicate.dsl.BooleanPredicateClausesStep;
import org.hibernate.search.engine.search.predicate.dsl.PredicateFinalStep;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class FirstNameQuery implements BiConsumer<BooleanPredicateClausesStep<?>, SearchRequest> {

    @Override
    public void accept(final BooleanPredicateClausesStep<?> query, final SearchRequest request) {
        final var firstName = request.getFirstName();
        final var searchType = request.getSearchType();
        if (StringUtils.hasText(firstName)) {

            if (searchType == SearchType.AND) {
                query.must(getFirstNameQuery(firstName));
            } else {
                query.should(getFirstNameQuery(firstName));
            }
        }
    }

    private static Function<SearchPredicateFactory, PredicateFinalStep> getFirstNameQuery(final String firstName) {
        return subQuery -> subQuery.terms()
                                   .field(FieldName.FIRSTNAME.name)
                                   .matchingAll(firstName)
                                   .boost(25.0f);
    }
}
