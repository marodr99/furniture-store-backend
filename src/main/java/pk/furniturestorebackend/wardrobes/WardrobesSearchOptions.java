package pk.furniturestorebackend.wardrobes;

import lombok.Data;
import pk.furniturestorebackend.database.furniture.SortOption;
import pk.furniturestorebackend.database.furniture.wardrobes.WardrobeMaterial;

@Data
public class WardrobesSearchOptions {
    private String title;
    private String color;
    private WardrobeMaterial wardrobeMaterial;
    private double startPrice;
    private double endPrice;
    private SortOption sortOption;
}
