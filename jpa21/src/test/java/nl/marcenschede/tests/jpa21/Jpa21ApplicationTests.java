package nl.marcenschede.tests.jpa21;

import nl.marcenschede.tests.jpa21.entities.Order;
import nl.marcenschede.tests.jpa21.entities.OrderItem;
import nl.marcenschede.tests.jpa21.entities.Product;
import nl.marcenschede.tests.jpa21.repositories.OrderFullRepository;
import nl.marcenschede.tests.jpa21.repositories.OrderItemRepository;
import nl.marcenschede.tests.jpa21.repositories.OrderRepository;
import nl.marcenschede.tests.jpa21.repositories.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Jpa21Application.class)
@WebAppConfiguration
public class Jpa21ApplicationTests {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderFullRepository orderFullRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private ProductRepository productRepository;

	@Before
	public void before() {
		Product product = new Product();
		product.setName("Product");
		productRepository.save(product);

		Order order = new Order();
		order.setOrderNumber("Order20160001");
		orderRepository.save(order);

		OrderItem orderItem = new OrderItem();
		orderItem.setProduct(product);
		orderItem.setQuantity(1);
		orderItem.setOrder(order);
		orderItemRepository.save(orderItem);
	}

	@Test
	public void shouldLoadOrderWithItem() throws Exception {

		Order order = orderRepository.findOne(1L);

		// Nog niet helemaal duidelijk wat er nu terug komt
//		Assert.assertThat(order.getItem().getProduct(), Matchers.nullValue());
	}

	@Test
	public void shouldLoadOrderWithItemAndProduct() throws Exception {

		Order order = orderFullRepository.findOne(1L);

		// Nog niet helemaal duidelijk wat er nu terug komt
//		Assert.assertThat(order.getItem().getProduct(), Matchers.notNullValue());
	}

}
