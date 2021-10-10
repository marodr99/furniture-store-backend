package pk.furniturestorebackend.chairs;

import lombok.Data;
import pk.furniturestorebackend.database.furniture.Furniture;
import pk.furniturestorebackend.database.furniture.FurnitureType;
import pk.furniturestorebackend.database.furniture.chairs.Chair;
import pk.furniturestorebackend.database.furniture.chairs.ChairMaterial;

@Data
public class SingleChairResponse {
    private Integer id;
    private String title;
    private double price;
    private String imgUrl;
    private FurnitureType furnitureType;
    private int maxWeight;
    private int width;
    private int height;
    private int depth;
    private String color;
    private ChairMaterial chairMaterial;
    private String additionalInformation;
    private int stock;
    private String fileName;
    private String imagesUrl;

    public SingleChairResponse(Chair chair) {
        Furniture furniture = chair.getFurniture();
        this.id = chair.getId();
        this.title = furniture.getTitle();
        this.price = furniture.getPrice();
        this.imgUrl = furniture.getImgUrl();
        this.furnitureType = furniture.getFurnitureType();
        this.maxWeight = chair.getMaxWeight();
        this.width = chair.getWidth();
        this.height = chair.getHeight();
        this.depth = chair.getDepth();
        this.color = chair.getColor();
        this.chairMaterial = chair.getMaterial();
        this.additionalInformation = chair.getAdditionalInformation();
        this.stock = chair.getStock();
        this.fileName = chair.getFileName();
        this.imagesUrl = chair.getImagesUrl();
    }
}
