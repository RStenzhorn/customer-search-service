package de.rjst.css.container;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testcontainers.utility.DockerImageName;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageConfig {

    public static final DockerImageName ELASTIC = DockerImageName.parse("opensearchproject/opensearch:2.19.2").asCompatibleSubstituteFor("docker.elastic.co/elasticsearch/elasticsearch");
    public static final String KIBANA = "opensearchproject/opensearch-dashboards:2.19.2";
    public static final DockerImageName POSTGRESQL = DockerImageName.parse("postgres:17.5");

}
