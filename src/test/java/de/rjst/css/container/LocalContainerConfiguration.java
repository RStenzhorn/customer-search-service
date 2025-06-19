package de.rjst.css.container;

import static de.rjst.css.container.ImageConfig.ELASTIC;
import static de.rjst.css.container.ImageConfig.KIBANA;
import static de.rjst.css.container.ImageConfig.POSTGRESQL;

import java.util.List;
import org.opensearch.testcontainers.OpensearchContainer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistrar;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration(proxyBeanMethods = false)
public class LocalContainerConfiguration {

    @Bean
    public OpensearchContainer elasticsearchContainer() {
        OpensearchContainer opensearchContainer = new OpensearchContainer(ELASTIC);
        opensearchContainer.withNetworkAliases("opensearch");
        opensearchContainer.withNetwork(Network.SHARED);
        return opensearchContainer;
    }

    @Bean
    @ServiceConnection
    public PostgreSQLContainer<?> postgresContainer() {
        var container = new PostgreSQLContainer<>(POSTGRESQL);
        container.withUsername("postgres");
        container.withPassword("123");
        container.setPortBindings(List.of("5432:5432"));
        return container;
    }

    @Bean
    public KibanaContainer kibanaContainer(final OpensearchContainer elasticsearchContainer) {
        final var container = new KibanaContainer(KIBANA)
            .withNetwork(Network.SHARED)
            .dependsOn(elasticsearchContainer);
        container.setPortBindings(List.of("5601:5601"));
        return container;
    }


    @Bean
    public DynamicPropertyRegistrar propertyEditorRegistrar(final OpensearchContainer elasticsearchContainer) {
        elasticsearchContainer.start();
        var url = elasticsearchContainer.getHost() + ":" + elasticsearchContainer.getMappedPort(9200);
        return registry -> {
            registry.add("spring.jpa.properties.hibernate.search.backend.hosts", () -> url);
        };
    }


}
