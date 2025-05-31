package de.rjst.css.container;

import java.time.Duration;
import org.testcontainers.containers.GenericContainer;

public class KibanaContainer extends GenericContainer<KibanaContainer> {

    private static final int KIBANA_PORT = 5601;

    public KibanaContainer(final String dockerImageName) {
        super(dockerImageName);
        this.withExposedPorts(KIBANA_PORT)
            .withStartupTimeout(Duration.ofMinutes(2L))
            .withEnv("xpack.security.enabled", "false");
    }
}

