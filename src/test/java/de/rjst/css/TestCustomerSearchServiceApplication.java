package de.rjst.css;

import de.rjst.css.container.LocalContainerConfiguration;
import org.springframework.boot.SpringApplication;

public class TestCustomerSearchServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(CustomerSearchServiceApplication::main)
                         .with(LocalContainerConfiguration.class)
                         .run(args);
    }

}
