package de.rjst.css.search.query;

import de.rjst.css.api.SearchType;
import de.rjst.css.api.model.SearchRequest;
import de.rjst.css.database.FieldName;
import java.util.function.BiConsumer;
import java.util.function.Function;
import org.hibernate.search.engine.search.predicate.dsl.BooleanPredicateClausesStep;
import org.hibernate.search.engine.search.predicate.dsl.PredicateFinalStep;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class EmailQuery implements BiConsumer<BooleanPredicateClausesStep<?>, SearchRequest> {

    @Override
    public void accept(final BooleanPredicateClausesStep<?> query, final SearchRequest request) {
        final var email = request.getEmail();
        final var searchType = request.getSearchType();
        if (StringUtils.hasText(email)) {
            if (searchType == SearchType.AND) {
                query.must(getEmailTermQuery(email));
            } else {
                query.should(getEmailTermQuery(email));
            }
        }
    }

    @NonNull
    private static Function<SearchPredicateFactory, PredicateFinalStep> getEmailTermQuery(final String email) {
        return subQuery -> subQuery.terms()
                                   .field(FieldName.EMAIL.name)
                                   .matchingAll(email)
                                   .boost(100.0f);

    }

}
