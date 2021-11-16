package pk.furniturestorebackend.database.furniture.wardrobes;

import lombok.Data;
import pk.furniturestorebackend.database.furniture.Furniture;

import javax.persistence.*;

@Data
@Entity
@Table(name = "wardrobes")
public class Wardrobe {
    @Id
    private Integer furniture_id;

    @Column(nullable = false)
    private Integer width;
    @Column(nullable = false)
    private Integer height;
    @Column(nullable = false)
    private Integer depth;
    @Column(nullable = false)
    private String color;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private WardrobeMaterial material;
    private String additionalInformation;
    @Column(nullable = false)
    private Integer stock;
    @Column(nullable = false)
    private String fileName;
    @Column(nullable = false)
    private String imagesUrl;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    private Furniture furniture;
}
