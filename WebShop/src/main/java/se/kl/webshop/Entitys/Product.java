package se.kl.webshop.Entitys;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private int price;
    @ManyToOne
    @JoinColumn(name = "type", referencedColumnName = "type_id")
    private ProductType productType;

    //Constructor
    public Product(String name, ProductType productType) {
        this.name = name;
        this.productType = productType;
    }
    public Product() {

    }

    //Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
    public ProductType getProductType() {
        return productType;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}
