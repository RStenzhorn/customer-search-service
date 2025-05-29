package de.rjst.css;

import java.util.List;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistrar;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

    @Bean
    public ElasticsearchContainer elasticsearchContainer() {
        return new ElasticsearchContainer(DockerImageName.parse("docker.elastic.co/elasticsearch/elasticsearch:8.18.1"))
            .withNetwork(Network.SHARED)
            .withNetworkAliases("elasticsearch")
            .withEnv("xpack.security.enabled", "false");
    }

    @Bean
    @ServiceConnection
    public PostgreSQLContainer<?> postgresContainer() {
        return new PostgreSQLContainer<>(DockerImageName.parse("postgres:17.5"));
    }

    @Bean
    public KibanaContainer kibanaContainer(final ElasticsearchContainer elasticsearchContainer) {
        final var container = new KibanaContainer("docker.elastic.co/kibana/kibana:8.18.1")
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
