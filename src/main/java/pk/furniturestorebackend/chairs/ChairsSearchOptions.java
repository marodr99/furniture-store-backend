package pk.furniturestorebackend.chairs;

import lombok.Data;
import pk.furniturestorebackend.database.furniture.SortOption;
import pk.furniturestorebackend.database.furniture.chairs.ChairMaterial;

@Data
public class ChairsSearchOptions {
    private String title;
    private String color;
    private ChairMaterial chairMaterial;
    private double startPrice;
    private double endPrice;
    private SortOption sortOption;
}
