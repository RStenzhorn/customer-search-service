package de.rjst.css.customer;

import com.github.javafaker.Faker;
import de.rjst.css.database.CustomerEntity;
import de.rjst.css.database.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private static final Faker faker = new Faker();

    private final CustomerRepository customerRepository;

    @PostMapping
    public List<CustomerEntity> createTestData(@RequestParam final int count) {
        final List<CustomerEntity> customers = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            final var customer = new CustomerEntity();
            customer.setFirstName(faker.name()
                                       .firstName());
            customer.setLastName(faker.name()
                                      .lastName());
            customer.setEmail(faker.internet()
                                   .emailAddress());
            customer.setPhone(faker.phoneNumber()
                                   .phoneNumber());
            customer.setCustomerId((faker.number().digits(10)));
            customers.add(customerRepository.save(customer));
        }

        return customers;
    }

}
