package jparestaurantmenu;

import javax.persistence.*;

@Entity
@Table(name = "dishes")

public class MenuDish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dish_id")
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private double weight;

    @Column
    private boolean discount;

    public MenuDish() {
    }

    public MenuDish(String name, double price, double weight, boolean discount) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.discount = discount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public boolean getDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "MenuDish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", weight='" + weight + '\'' +
                ", discount=" + discount +
                '}';
    }
}