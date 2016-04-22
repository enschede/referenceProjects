package nl.marcenschede.tests.jpa21.repositories;

import nl.marcenschede.tests.jpa21.entities.OrderItem;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by marc on 05/03/16.
 */
public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
}
