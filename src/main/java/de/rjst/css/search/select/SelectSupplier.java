package de.rjst.css.search.select;

import de.rjst.css.api.model.ElasticResponseDto;
import de.rjst.css.database.CustomerEntity;
import de.rjst.css.database.SearchHelper;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import org.hibernate.search.backend.elasticsearch.ElasticsearchExtension;
import org.hibernate.search.engine.search.query.dsl.SearchQueryWhereStep;
import org.hibernate.search.mapper.orm.search.loading.dsl.SearchLoadingOptionsStep;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SelectSupplier implements Supplier<SearchQueryWhereStep<?, ElasticResponseDto, SearchLoadingOptionsStep, ?>> {

    private final SearchHelper sessionHelper;

    @Override
    public SearchQueryWhereStep<?, ElasticResponseDto, SearchLoadingOptionsStep, ?> get() {
        final var session = sessionHelper.getSearchSession();
        return session.search(CustomerEntity.class)
                      .select(result -> result.composite()
                                    .from(result.entity(), result.score(), result.extension(ElasticsearchExtension.get())
                                                                  .explanation())
                                    .as(ElasticResponseDto::new));
    }
}
