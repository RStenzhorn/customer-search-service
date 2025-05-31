package de.rjst.css.database;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.search.engine.backend.types.Aggregable;
import org.hibernate.search.engine.backend.types.Projectable;
import org.hibernate.search.engine.backend.types.Searchable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Indexed(index = "customer")
@Entity
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @KeywordField(searchable = Searchable.YES)
    private String firstName;

    @KeywordField(searchable = Searchable.YES)
    private String lastName;

    @KeywordField(searchable = Searchable.YES)
    private String email;

    @KeywordField(searchable = Searchable.YES)
    private String phone;

    @Column(name = "customer_id", nullable = false)
    private String customerId;
}
