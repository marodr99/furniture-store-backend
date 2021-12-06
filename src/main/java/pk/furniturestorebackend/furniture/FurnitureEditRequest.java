package pk.furniturestorebackend.furniture;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FurnitureEditRequest {
    private Integer id;
    private String title;
    private Double price;
    private String imgUrl;
    private String furnitureType;
    private Integer maxWeight;
    private Integer width;
    private Integer height;
    private Integer depth;
    private String color;
    @JsonProperty("material")
    @JsonAlias({"chairMaterial", "wardrobeMaterial"})
    private String material;
    private String additionalInformation;
    private Integer stock;
    private String fileName;
    private String imagesUrl;
}
