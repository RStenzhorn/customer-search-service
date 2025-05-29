package de.rjst.css.database.elastic;

import org.hibernate.search.backend.elasticsearch.index.layout.IndexLayoutStrategy;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomIndexStrategy implements IndexLayoutStrategy {

    @Override
    public String createInitialElasticsearchIndexName(final String hibernateSearchIndexName) {
        return hibernateSearchIndexName;
    }

    @Override
    public String createWriteAlias(final String hibernateSearchIndexName) {
        return hibernateSearchIndexName + "-write";
    }

    @Override
    public String createReadAlias(final String hibernateSearchIndexName) {
        return hibernateSearchIndexName + "-read";
    }

    @Override
    public String extractUniqueKeyFromHibernateSearchIndexName(final String hibernateSearchIndexName) {
        return IndexLayoutStrategy.super.extractUniqueKeyFromHibernateSearchIndexName(hibernateSearchIndexName);
    }

    @Override
    public String extractUniqueKeyFromElasticsearchIndexName(final String elasticsearchIndexName) {
        return IndexLayoutStrategy.super.extractUniqueKeyFromElasticsearchIndexName(elasticsearchIndexName);
    }
}
