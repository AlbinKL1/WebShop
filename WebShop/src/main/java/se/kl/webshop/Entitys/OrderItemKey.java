package se.kl.webshop.Entitys;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class OrderItemKey implements Serializable {

    private int orderId;
    private int productId;

    //Getters
    public int getOrderId() {
        return orderId;
    }
    public int getProductId() {
        return productId;
    }

    //Setters
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
}
