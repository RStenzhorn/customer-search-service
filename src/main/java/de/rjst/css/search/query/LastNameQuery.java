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
public class LastNameQuery implements BiConsumer<BooleanPredicateClausesStep<?>, SearchRequest> {

    @Override
    public void accept(final BooleanPredicateClausesStep<?> query, final SearchRequest request) {
        final var lastName = request.getLastName();
        final var searchType = request.getSearchType();
        if (StringUtils.hasText(lastName)) {
            if (searchType == SearchType.AND) {
                query.must(getLastNameQuery(lastName));
            } else {
                query.should(getLastNameQuery(lastName));
            }
        }
    }

    private static Function<SearchPredicateFactory, PredicateFinalStep> getLastNameQuery(String lastName) {
        return subQuery -> subQuery.terms()
                                   .field(FieldName.LASTNAME.name)
                                   .matchingAll(lastName)
                                   .boost(25.0f);
    }
}
