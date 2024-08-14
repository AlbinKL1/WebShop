package se.kl.webshop.Entitys;

import jakarta.persistence.*;

@Entity
@Table(name="order_process")
public class OrderProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "user_id", insertable = false, updatable = false)
    private int userId;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "product_id", insertable = false, updatable = false)
    private int productId;

    //Getters
    public int getId() {
        return id;
    }
    public User getUser() {
        return user;
    }
    public int getUserId() {
        return userId;
    }
    public Product getProduct() {
        return product;
    }
    public int getProductId() {
        return productId;
    }

    //Setters
    public void setUser(User user) {
        this.user = user;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
}
