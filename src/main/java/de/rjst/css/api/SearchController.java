package de.rjst.css.api;

import de.rjst.css.api.model.SearchRequestDto;
import de.rjst.css.api.model.ElasticResponseDto;
import de.rjst.css.api.model.SearchResponse;
import de.rjst.css.database.CustomerEntity;
import de.rjst.css.database.FieldNames;
import de.rjst.css.database.SearchHelper;
import de.rjst.css.logic.MatchedFieldMapper;
import de.rjst.css.logic.SearchResponseMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.hibernate.search.backend.elasticsearch.ElasticsearchExtension;
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
        SearchSession session = sessionHelper.getSearchSession();

        var searchResult = session.search(CustomerEntity.class)
                                  .select(f -> f.composite()
                                                .from(f.entity(), f.score(), f.extension(ElasticsearchExtension.get()).explanation())
                                                .as(ElasticResponseDto::new))
                                  .where(f -> f.bool()
                                             .minimumShouldMatchNumber(1)
                                             .should(x -> x.terms()
                                                           .field(FieldNames.FIRSTNAME.name)
                                                           .matchingAll(request.getFirstName())
                                                     .boost(10.0f))
                                             .should(x -> x.terms()
                                                           .field(FieldNames.LASTNAME.name)
                                                           .matchingAll(request.getLastName())
                                                     .boost(5.0f))
                                  )
                                  .fetchAll();

        return searchResult.hits().stream().map(searchResponseMapper).toList();
    }
}
