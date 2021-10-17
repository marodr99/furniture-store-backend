package pk.furniturestorebackend.database.furniture.chairs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChairRepository extends JpaRepository<Chair, Integer> {
    @Query("SELECT DISTINCT c.color FROM Chair c")
    List<String> getAllColors();

    @Query("SELECT DISTINCT c.material FROM Chair c")
    List<String> getAllMaterials();
}
