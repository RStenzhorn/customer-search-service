package de.rjst.css.container;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testcontainers.utility.DockerImageName;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageConfig {

    public static final DockerImageName ELASTIC = DockerImageName.parse("docker.elastic.co/elasticsearch/elasticsearch:8.18.1");
    public static final String KIBANA = "docker.elastic.co/kibana/kibana:8.18.1";
    public static final DockerImageName POSTGRESQL = DockerImageName.parse("postgres:17.5");

}
