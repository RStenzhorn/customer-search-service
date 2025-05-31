package de.rjst.css;

import de.rjst.css.container.JUnitContainerConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(JUnitContainerConfiguration.class)
@SpringBootTest
class CustomerSearchServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
