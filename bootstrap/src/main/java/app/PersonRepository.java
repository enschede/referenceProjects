package app;

import org.springframework.data.repository.CrudRepository;

/**
 * Test repository
 */
public interface PersonRepository extends CrudRepository<Person, Long> {

}
