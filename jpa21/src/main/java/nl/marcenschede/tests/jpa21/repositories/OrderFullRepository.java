package nl.marcenschede.tests.jpa21.repositories;

import nl.marcenschede.tests.jpa21.entities.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by marc on 05/03/16.
 */
public interface OrderFullRepository extends CrudRepository<Order, Long> {

    @EntityGraph(value = "full")
    public Order findById(Long id);

}
