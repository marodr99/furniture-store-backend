package pk.furniturestorebackend.database.furniture;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pk.furniturestorebackend.database.furniture.chairs.ChairMaterial;
import pk.furniturestorebackend.database.furniture.wardrobes.WardrobeMaterial;

@Repository
public interface FurnitureRepository extends JpaRepository<Furniture, Integer> {
    Page<Furniture> findAllByFurnitureType(FurnitureType furnitureType, Pageable pageable);

    @Query("SELECT c.furniture FROM Chair c JOIN c.furniture f WHERE (:title is null or f.title LIKE concat(:title, '%')) " +
            "and (:color is null or c.color = :color) and " +
            "(:chairMaterial is null or c.material = :chairMaterial) and " +
            "(c.furniture.price >= :startPrice) and (:endPrice = 0.0 or c.furniture.price <= :endPrice)")
    Page<Furniture> findSpecificWardrobes(String title, String color, ChairMaterial chairMaterial, double startPrice,
                                          double endPrice, Pageable pageable);

    @Query("SELECT c.furniture FROM Wardrobe c JOIN c.furniture f WHERE (:title is null or f.title LIKE concat(:title, '%')) " +
            "and (:color is null or c.color = :color) and " +
            "(:wardrobeMaterial is null or c.material = :wardrobeMaterial) and " +
            "(c.furniture.price >= :startPrice) and (:endPrice = 0.0 or c.furniture.price <= :endPrice)")
    Page<Furniture> findSpecificWardrobes(String title, String color, WardrobeMaterial wardrobeMaterial, double startPrice,
                                          double endPrice, Pageable pageable);

    @Query("SELECT c.stock FROM Chair c WHERE c.furniture.id = (:furnitureId)")
    Integer getStockForChair(Integer furnitureId);

    @Query("SELECT w.stock FROM Wardrobe w WHERE w.furniture.id = (:furnitureId)")
    Integer getStockForWardrobe(Integer furnitureId);
}
