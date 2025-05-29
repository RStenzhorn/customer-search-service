package de.rjst.css.api;

import de.rjst.css.database.CustomerEntity;
import de.rjst.css.database.SearchHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/index")
public class IndexController {

    private final SearchHelper searchHelper;

    @Transactional
    @PostMapping
    public void index() throws InterruptedException {
        final var searchSession = searchHelper.getSearchSession();

        searchSession.massIndexer(CustomerEntity.class)
                     .startAndWait();
    }

}
