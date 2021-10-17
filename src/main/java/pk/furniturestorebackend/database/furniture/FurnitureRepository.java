package pk.furniturestorebackend.database.furniture;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pk.furniturestorebackend.database.furniture.chairs.ChairMaterial;

@Repository
public interface FurnitureRepository extends JpaRepository<Furniture, Integer> {
    Page<Furniture> findAllByFurnitureType(FurnitureType furnitureType, Pageable pageable);

    @Query("SELECT c.furniture FROM Chair c JOIN c.furniture f WHERE (:title is null or f.title LIKE concat(:title, '%')) " +
            "and (:color is null or c.color = :color) and " +
            "(:chairMaterial is null or c.material = :chairMaterial) and " +
            "(c.furniture.price >= :startPrice) and (:endPrice = 0.0 or c.furniture.price <= :endPrice)")
    Page<Furniture> findSpecificChairs(String title, String color, ChairMaterial chairMaterial, double startPrice,
                                       double endPrice, Pageable pageable);
}
