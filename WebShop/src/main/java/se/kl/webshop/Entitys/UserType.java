package se.kl.webshop.Entitys;


import jakarta.persistence.*;

@Entity
@Table(name = "usertype")
public class UserType {

    @Id
    private int id;
    @Column(nullable = false)
    private String type;

    //Getters
    public int getId() {
        return id;
    }
    public String getType() {
        return type;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setType(String type) {
        this.type = type;
    }
}