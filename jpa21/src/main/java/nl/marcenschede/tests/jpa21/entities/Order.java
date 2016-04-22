package nl.marcenschede.tests.jpa21.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "purchaseOrder")
@NamedEntityGraphs({

        @NamedEntityGraph(name = "item",
        // Entity graph name='item' zorgt voo eager loading van NamedAttributeNode='item'
        // Subgraph zorgt voor loading van attribuut in item
        attributeNodes = @NamedAttributeNode(value = "item")),

        @NamedEntityGraph(name = "full",
        // Entity graph name='item' zorgt voo eager loading van NamedAttributeNode='item'
        // Subgraph zorgt voor loading van attribuut in item
        attributeNodes = @NamedAttributeNode(value = "item", subgraph = "product"),
        subgraphs = @NamedSubgraph(name = "product", attributeNodes = @NamedAttributeNode("product")))
})

public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id = null;

    @Version
    @Column(name = "version")
    private int version = 0;

    @Column
    private String orderNumber;

    @OneToOne(mappedBy = "order")
    private OrderItem item;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public OrderItem getItem() {
        return item;
    }

    public void setItem(OrderItem item) {
        this.item = item;
    }
}