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
public class PhoneQuery implements BiConsumer<BooleanPredicateClausesStep<?>, SearchRequest> {

    @Override
    public void accept(final BooleanPredicateClausesStep<?> query, final SearchRequest request) {
        final var phone = request.getPhone();
        final var searchType = request.getSearchType();
        if (StringUtils.hasText(phone) && phone.length() >= 5) {
            final String prefix = phone.substring(0, 5);
            final Function<SearchPredicateFactory, PredicateFinalStep> termQuery = getPhoneQuery(prefix);
            if (searchType == SearchType.AND) {
                query.must(termQuery);
            } else {
                query.should(termQuery);
            }
        }
    }

    private static Function<SearchPredicateFactory, PredicateFinalStep> getPhoneQuery(String prefix) {
        return subQuery -> subQuery.wildcard()
                                   .field(FieldName.PHONE.name)
                                   .matching(prefix + "*")
                                   .boost(2.5f);
    }
}
