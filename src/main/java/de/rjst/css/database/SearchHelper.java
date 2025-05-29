package de.rjst.css.database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.stereotype.Service;

@Service
public class SearchHelper {

    @PersistenceContext
    private EntityManager entityManager;

    public SearchSession getSearchSession() {
        return Search.session(entityManager);
    }
}

