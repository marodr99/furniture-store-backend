package pk.furniturestorebackend.furniture;

import lombok.Data;
import pk.furniturestorebackend.database.furniture.Furniture;

import java.util.List;

@Data
public class FurnitureResponse {
    private Integer totalNumberOfPages;
    private List<Furniture> furnitureFromPage;

    public FurnitureResponse(Integer page, List<Furniture> furniture) {
        this.totalNumberOfPages = page;
        this.furnitureFromPage = furniture;
    }
}
