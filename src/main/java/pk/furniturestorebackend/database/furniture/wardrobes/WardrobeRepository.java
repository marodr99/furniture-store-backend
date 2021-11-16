package pk.furniturestorebackend.database.furniture.wardrobes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WardrobeRepository extends JpaRepository<Wardrobe, Integer> {
    @Query("SELECT DISTINCT w.color FROM Wardrobe w")
    List<String> getAllColors();

    @Query("SELECT DISTINCT w.material FROM Wardrobe w")
    List<String> getAllMaterials();
}
