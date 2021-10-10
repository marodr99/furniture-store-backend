package pk.furniturestorebackend.database.furniture.chairs;


import lombok.Data;
import pk.furniturestorebackend.database.furniture.Furniture;

import javax.persistence.*;

@Data
@Entity
@Table(name = "chairs")
public class Chair {
    @Id
    private Integer id;

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
    @Enumerated(EnumType.STRING)
    private ChairMaterial material;
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
