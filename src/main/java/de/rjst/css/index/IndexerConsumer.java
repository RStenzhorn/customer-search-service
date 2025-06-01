package de.rjst.css.index;

import static de.rjst.css.database.elastic.IndexName.CUSTOMER;

import de.rjst.css.database.CustomerEntity;
import de.rjst.css.database.SearchHelper;
import java.util.Map;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class IndexerConsumer implements Consumer<String> {

    private static final Map<String, Class<?>> INDEXES = Map.of(CUSTOMER, CustomerEntity.class);

    private final SearchHelper searchHelper;

    @SneakyThrows
    @Transactional
    @Override
    public void accept(final String indexName) {
        final var session = searchHelper.getSearchSession();

        final var entity = INDEXES.get(indexName);
        if (entity != null) {
            final var massIndexer = session.massIndexer(CustomerEntity.class);
            massIndexer.startAndWait();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Index not found");
        }
    }
}
