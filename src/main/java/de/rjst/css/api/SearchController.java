package de.rjst.css.api;

import de.rjst.css.api.model.SearchRequestDto;
import de.rjst.css.database.CustomerEntity;
import de.rjst.css.database.SearchHelper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.hibernate.search.engine.search.query.SearchResult;
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

    @Transactional
    @PostMapping
    public List<CustomerEntity> search(@RequestBody final SearchRequestDto request) {
        SearchSession session = sessionHelper.getSearchSession();

        var searchResult = session.search(CustomerEntity.class)
                                                           .where(f -> f.bool()
                                                                        .minimumShouldMatchNumber(1)
                                                                        .should(x -> x.terms()
                                                                                      .field("firstName")
                                                                                      .matchingAll(request.getFirstName()))
                                                                        .should(x -> x.terms()
                                                                                      .field("lastName")
                                                                                      .matchingAll(request.getLastName()))
                                                           )
                                                           .highlighter(h -> h.unified()
                                                                              .numberOfFragments(3)
                                                                              .fragmentSize(150)
                                                                              .tag("<mark>", "</mark>\n"))
                                                           .fetch(20);

        return searchResult.hits();
    }
}
