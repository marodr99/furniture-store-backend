package pk.furniturestorebackend.database.furniture;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FurnitureRepository extends JpaRepository<Furniture, Integer> {
    Page<Furniture> findAllByFurnitureType(FurnitureType furnitureType, Pageable pageable);
}
