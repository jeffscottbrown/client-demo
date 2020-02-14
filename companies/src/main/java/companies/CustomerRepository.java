package companies;

import companies.model.Company;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

@JdbcRepository(dialect = Dialect.H2)
public interface CustomerRepository extends CrudRepository<Company, Long> {
    Company persist(String name);
    Iterable<Company> listOrderByName();
}
