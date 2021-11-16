package pk.furniturestorebackend.wardrobes;

import lombok.Data;
import pk.furniturestorebackend.database.furniture.Furniture;
import pk.furniturestorebackend.database.furniture.FurnitureType;
import pk.furniturestorebackend.database.furniture.wardrobes.Wardrobe;
import pk.furniturestorebackend.database.furniture.wardrobes.WardrobeMaterial;

@Data
public class SingleWardrobeResponse {
    private Integer id;
    private String title;
    private double price;
    private String imgUrl;
    private FurnitureType furnitureType;
    private int width;
    private int height;
    private int depth;
    private String color;
    private WardrobeMaterial wardrobeMaterial;
    private String additionalInformation;
    private int stock;
    private String fileName;
    private String imagesUrl;

    public SingleWardrobeResponse(Wardrobe wardrobe) {
        Furniture furniture = wardrobe.getFurniture();
        this.id = wardrobe.getFurniture_id();
        this.title = furniture.getTitle();
        this.price = furniture.getPrice();
        this.imgUrl = furniture.getImgUrl();
        this.furnitureType = furniture.getFurnitureType();
        this.width = wardrobe.getWidth();
        this.height = wardrobe.getHeight();
        this.depth = wardrobe.getDepth();
        this.color = wardrobe.getColor();
        this.wardrobeMaterial = wardrobe.getMaterial();
        this.additionalInformation = wardrobe.getAdditionalInformation();
        this.stock = wardrobe.getStock();
        this.fileName = wardrobe.getFileName();
        this.imagesUrl = wardrobe.getImagesUrl();
    }
}
