package nl.marcenschede.tests.jpa21.repositories;

import nl.marcenschede.tests.jpa21.entities.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by marc on 05/03/16.
 */
public interface OrderRepository extends CrudRepository<Order, Long> {

    // Deze deze regel weg te laten wordt hier alleen de order genomen
    @EntityGraph(value = "item")
    public Order findById(Long id);

}
