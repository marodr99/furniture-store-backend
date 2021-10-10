package pk.furniturestorebackend.furniture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pk.furniturestorebackend.database.furniture.Furniture;
import pk.furniturestorebackend.database.furniture.FurnitureRepository;
import pk.furniturestorebackend.database.furniture.FurnitureType;

import java.util.List;

@Service
public class FurnitureService {
    private static final int PAGE_RESULT_SIZE = 30;
    private final FurnitureRepository furnitureRepository;

    @Autowired
    public FurnitureService(FurnitureRepository furnitureRepository) {
        this.furnitureRepository = furnitureRepository;
    }

    public Page<Furniture> getAllFurniture(FurnitureType furnitureType, Integer page) {
        if(page < 0) {
            throw new IllegalArgumentException("Page number is incorrect");
        }
        PageRequest pageRequest = PageRequest.of(page, PAGE_RESULT_SIZE);
        return furnitureRepository.findAllByFurnitureType(furnitureType, pageRequest);
    }
}
