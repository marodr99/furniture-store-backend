package pk.furniturestorebackend.database.furniture.chairs;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "chairs")
public class Chair {

    private Chair() {
    }

    public Chair(Integer id, String title, Integer maxWeight, Integer width, Integer height, Integer depth,
                 String color, Double price, ChairMaterial material, String additionalInformation, Integer stock,
                 String imageUrl, String fileName) {
        this.id = id;
        this.title = title;
        this.maxWeight = maxWeight;
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.color = color;
        this.price = price;
        this.material = material;
        this.additionalInformation = additionalInformation;
        this.stock = stock;
        this.imageUrl = imageUrl;
        this.fileName = fileName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private Integer maxWeight;
    @Column(nullable = false)
    private Integer width;
    @Column(nullable = false)
    private Integer height;
    @Column(nullable = false)
    private Integer depth;
    @Column(nullable = false)
    private String color;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ChairMaterial material;
    private String additionalInformation;
    @Column(nullable = false)
    private Integer stock;
    @Column(nullable = false)
    private String imageUrl;
    @Column(nullable = false)
    private String fileName;
}
