package de.rjst.css.container;

import static de.rjst.css.container.ImageConfig.ELASTIC;
import static de.rjst.css.container.ImageConfig.KIBANA;
import static de.rjst.css.container.ImageConfig.POSTGRESQL;

import java.util.List;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistrar;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.elasticsearch.ElasticsearchContainer;

@TestConfiguration(proxyBeanMethods = false)
public class LocalContainerConfiguration {

    @Bean
    public ElasticsearchContainer elasticsearchContainer() {
        return new ElasticsearchContainer(ELASTIC)
            .withNetwork(Network.SHARED)
            .withNetworkAliases("elasticsearch")
            .withEnv("xpack.security.enabled", "false");
    }

    @Bean
    @ServiceConnection
    public PostgreSQLContainer<?> postgresContainer() {
        return new PostgreSQLContainer<>(POSTGRESQL);
    }

    @Bean
    public KibanaContainer kibanaContainer(final ElasticsearchContainer elasticsearchContainer) {
        final var container = new KibanaContainer(KIBANA)
            .withNetwork(Network.SHARED)
            .dependsOn(elasticsearchContainer);
        container.setPortBindings(List.of("5601:5601"));
        return container;
    }


    @Bean
    public DynamicPropertyRegistrar propertyEditorRegistrar(final ElasticsearchContainer elasticsearchContainer) {
        elasticsearchContainer.start();
        return registry -> {
            registry.add("spring.jpa.properties.hibernate.search.backend.hosts", elasticsearchContainer::getHttpHostAddress);
        };
    }


}
