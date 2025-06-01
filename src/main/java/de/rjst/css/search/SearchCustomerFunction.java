package de.rjst.css.search;

import de.rjst.css.api.model.SearchRequest;
import de.rjst.css.api.model.SearchResponse;
import de.rjst.css.logic.SearchResponseMapper;
import de.rjst.css.search.query.QuerySupplier;
import de.rjst.css.search.select.SelectSupplier;
import java.util.List;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SearchCustomerFunction implements Function<SearchRequest, List<SearchResponse>> {

    private final SelectSupplier selectSupplier;
    private final QuerySupplier querySupplier;
    private final SearchResponseMapper searchResponseMapper;


    @Transactional
    @Override
    public List<SearchResponse> apply(final SearchRequest request) {
        final var select = selectSupplier.get();
        final var query = querySupplier.apply(request);


        final var elasticResponse = select.where(query)
                                          .fetch(100);

        return elasticResponse.hits()
                              .stream()
                              .map(searchResponseMapper)
                              .toList();
    }
}
