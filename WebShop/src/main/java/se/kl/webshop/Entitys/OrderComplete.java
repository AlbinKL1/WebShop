package se.kl.webshop.Entitys;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="order_complete")
public class OrderComplete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    //Getters
    public int getId() {
        return id;
    }
    public int getUserId() {
        return userId;
    }
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
    public Date getCreatedAt() {
        return createdAt;
    }

    //Setters
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
