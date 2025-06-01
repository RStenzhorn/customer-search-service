package de.rjst.css.search.query;

import de.rjst.css.database.CustomerEntity;
import de.rjst.css.database.SearchHelper;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import org.hibernate.search.engine.search.predicate.dsl.BooleanPredicateClausesStep;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BoolQuerySupplier implements Supplier<BooleanPredicateClausesStep<?>> {

    private final SearchHelper sessionHelper;

    @NonNull
    @Override
    public BooleanPredicateClausesStep<?> get() {
        final SearchSession session = sessionHelper.getSearchSession();

        return session.scope(CustomerEntity.class)
                      .predicate()
                      .bool();
    }
}
