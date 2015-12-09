package nl.marcenschede.springtest.app;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Test repository
 */
public interface PersonRepository extends CrudRepository<Person, Long> {

    public Optional<Person> findById(Long id);
    
}
