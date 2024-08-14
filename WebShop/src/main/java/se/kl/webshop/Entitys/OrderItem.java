package se.kl.webshop.Entitys;

import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @EmbeddedId
    private OrderItemKey id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private OrderComplete order;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    //Getters
    public OrderItemKey getId() {
        return id;
    }
    public OrderComplete getOrder() {
        return order;
    }
    public Product getProduct() {
        return product;
    }
    public int getQuantity() {
        return quantity;
    }

    //Setters
    public void setId(OrderItemKey id) {
        this.id = id;
    }
    public void setOrder(OrderComplete order) {
        this.order = order;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public void setQuantity(int quantitiy) {
        this.quantity = quantitiy;
    }
}
