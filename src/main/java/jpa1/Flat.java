package jpa1;

import javax.persistence.*;

@Entity
@Table(name = "flats")

public class Flat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "flat_id")
    private long id;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private String address;

    private int square;

    private int room_qty;

    private long price;

    public Flat() {
    }

    public Flat(String district, String address, int square, int room_qty, long price) {
        this.id = id;
        this.district = district;
        this.address = address;
        this.square = square;
        this.room_qty = room_qty;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSquare() {
        return square;
    }

    public void setSquare(int square) {
        this.square = square;
    }

    public int getRoom_qty() {
        return room_qty;
    }

    public void setRoom_qty(int room_qty) {
        this.room_qty = room_qty;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Flat{" +
                "id=" + id +
                ", district='" + district + '\'' +
                ", address='" + address + '\'' +
                ", square=" + square +
                ", room_qty=" + room_qty +
                ", price=" + price +
                '}';
    }
}
