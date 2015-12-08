package java8.optionals;

import java.util.Optional;

/**
 * Created by marc on 21/12/14.
 */
public class SalesExecutive {
    
    private Optional<Order> order;

    public SalesExecutive(Order order) {
        this.order = Optional.ofNullable(order);
    }

    public Optional<Order> getOrder() {
        return order;
    }
}
