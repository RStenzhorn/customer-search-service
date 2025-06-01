package de.rjst.css.search.query;

import de.rjst.css.api.SearchType;
import de.rjst.css.api.model.SearchRequest;
import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.BooleanPredicateClausesStep;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QuerySupplier implements Function<SearchRequest, SearchPredicate> {

    private final BoolQuerySupplier boolQuerySupplier;
    private final Collection<BiConsumer<BooleanPredicateClausesStep<?>, SearchRequest>> queries;

    @Override
    public SearchPredicate apply(final SearchRequest request) {
        final var boolQuery = boolQuerySupplier.get();
        queries.forEach(query -> query.accept(boolQuery, request));

        if (request.getSearchType() == SearchType.OR) {
            boolQuery.minimumShouldMatchNumber(1);
        }

        return boolQuery.toPredicate();
    }
}
