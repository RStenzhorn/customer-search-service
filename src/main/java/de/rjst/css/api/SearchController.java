package de.rjst.css.api;

import de.rjst.css.api.model.ElasticResponseDto;
import de.rjst.css.api.model.SearchRequestDto;
import de.rjst.css.api.model.SearchResponse;
import de.rjst.css.database.CustomerEntity;
import de.rjst.css.database.FieldNames;
import de.rjst.css.database.SearchHelper;
import de.rjst.css.logic.SearchResponseMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.hibernate.search.backend.elasticsearch.ElasticsearchExtension;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/search")
public class SearchController {

    private final SearchHelper sessionHelper;
    private final SearchResponseMapper searchResponseMapper;


    @Transactional
    @PostMapping
    public List<SearchResponse> search(@RequestBody final SearchRequestDto request) {
        final SearchSession session = sessionHelper.getSearchSession();

        final SearchPredicateFactory predicate = session.scope(CustomerEntity.class)
                                                        .predicate();

        final SearchPredicate searchPredicate = predicate.bool()
                                                         .minimumShouldMatchNumber(1)
                                                         .should(x -> x.terms()
                                                                 .field(FieldNames.FIRSTNAME.name)
                                                                 .matchingAll(request.getFirstName())
                                                                 .boost(10.0f))
                                                         .should(x -> x.terms()
                                                                 .field(FieldNames.LASTNAME.name)
                                                                 .matchingAll(request.getLastName())
                                                                 .boost(5.0f))
                                                         .toPredicate();
        final var searchResult = session.search(CustomerEntity.class)
                                        .select(f -> f.composite()
                                                .from(f.entity(), f.score(), f.extension(ElasticsearchExtension.get())
                                                                              .explanation())
                                                .as(ElasticResponseDto::new))
                                        .where(searchPredicate)
                                        .fetchAll();

        return searchResult.hits()
                           .stream()
                           .map(searchResponseMapper)
                           .toList();
    }
}
