package de.rjst.css;

import org.springframework.boot.SpringApplication;

public class TestCustomerSearchServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(CustomerSearchServiceApplication::main)
                         .with(TestcontainersConfiguration.class)
                         .run(args);
    }

}
