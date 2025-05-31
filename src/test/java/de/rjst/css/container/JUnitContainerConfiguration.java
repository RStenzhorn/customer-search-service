package de.rjst.css.container;

import static de.rjst.css.container.ImageConfig.ELASTIC;
import static de.rjst.css.container.ImageConfig.POSTGRESQL;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistrar;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.elasticsearch.ElasticsearchContainer;

@TestConfiguration(proxyBeanMethods = false)
public class JUnitContainerConfiguration {

    @Bean
    public ElasticsearchContainer elasticsearchContainer() {
        return new ElasticsearchContainer(ELASTIC)
            .withNetworkAliases("elasticsearch")
            .withEnv("xpack.security.enabled", "false");
    }

    @Bean
    @ServiceConnection
    public PostgreSQLContainer<?> postgresContainer() {
        return new PostgreSQLContainer<>(POSTGRESQL);
    }


    @Bean
    public DynamicPropertyRegistrar propertyEditorRegistrar(final ElasticsearchContainer elasticsearchContainer) {
        elasticsearchContainer.start();
        return registry -> {
            registry.add("spring.jpa.properties.hibernate.search.backend.hosts", elasticsearchContainer::getHttpHostAddress);
        };
    }


}
