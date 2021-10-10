package pk.furniturestorebackend.database.furniture;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "furnitures")
public class Furniture {

    public Furniture() {
    }

    public Furniture(Integer id, String title, double price, String imgUrl, FurnitureType furnitureType) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.imgUrl = imgUrl;
        this.furnitureType = furnitureType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String imgUrl;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FurnitureType furnitureType;
}
