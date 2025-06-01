package de.rjst.css.database.elastic;

import org.hibernate.search.backend.elasticsearch.analysis.ElasticsearchAnalysisConfigurationContext;
import org.hibernate.search.backend.elasticsearch.analysis.ElasticsearchAnalysisConfigurer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomAnalysisConfigurer implements ElasticsearchAnalysisConfigurer {

    public static final String GERMAN_CHAR_NORMALIZER = "german_char_normalizer";
    public static final String GERMAN_CHAR_MAPPING = "german_char_mapping";

    @Override
    public void configure(ElasticsearchAnalysisConfigurationContext context) {
        context.charFilter(GERMAN_CHAR_MAPPING)
               .type("mapping")
               .param("mappings", new String[]{
                   "ä => ae",
                   "ö => oe", 
                   "ü => ue",
                   "Ä => Ae",
                   "Ö => Oe",
                   "Ü => Ue",
                   "ß => ss"
               });

        context.normalizer(GERMAN_CHAR_NORMALIZER).custom()
               .charFilters(GERMAN_CHAR_MAPPING)
               .tokenFilters("lowercase");
    }
}
