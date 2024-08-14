package se.kl.webshop.Entitys;

import jakarta.persistence.*;
import se.kl.webshop.Entitys.Product;

import java.util.List;

@Entity
@Table(name = "producttype")
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private int id;
    @Column(name = "type_name")
    private String name;
    @OneToMany(mappedBy = "productType", cascade = CascadeType.ALL)
    private List<Product> products;

    //Constructor
    public ProductType(String name, List<Product> products) {
        this.name = name;
        this.products = products;
    }
    public ProductType() {

    }

    //Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public List<Product> getProducts() {
        return products;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
