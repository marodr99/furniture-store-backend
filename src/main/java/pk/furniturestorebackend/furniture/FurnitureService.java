package pk.furniturestorebackend.furniture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pk.furniturestorebackend.chairs.ChairsSearchOptions;
import pk.furniturestorebackend.database.furniture.Furniture;
import pk.furniturestorebackend.database.furniture.FurnitureRepository;
import pk.furniturestorebackend.database.furniture.FurnitureType;
import pk.furniturestorebackend.database.furniture.SortOption;
import pk.furniturestorebackend.wardrobes.WardrobesSearchOptions;

@Service
public class FurnitureService {
    private static final int PAGE_RESULT_SIZE = 30;
    private final FurnitureRepository furnitureRepository;

    @Autowired
    public FurnitureService(FurnitureRepository furnitureRepository) {
        this.furnitureRepository = furnitureRepository;
    }

    public Page<Furniture> getAllFurniture(FurnitureType furnitureType, Integer page) {
        if (page < 0) {
            throw new IllegalArgumentException("Page number is incorrect");
        }
        PageRequest pageRequest = PageRequest.of(page, PAGE_RESULT_SIZE);
        return furnitureRepository.findAllByFurnitureType(furnitureType, pageRequest);
    }

    public Page<Furniture> getSpecificChairs(Integer page, ChairsSearchOptions chairsSearchOptions) {
        if (page < 0) {
            throw new IllegalArgumentException("Page number is incorrect");
        }
        PageRequest pageRequest = PageRequest.of(page, PAGE_RESULT_SIZE, getProperSortOption(chairsSearchOptions.getSortOption()));
        return furnitureRepository.findSpecificWardrobes(chairsSearchOptions.getTitle(), chairsSearchOptions.getColor(),
                chairsSearchOptions.getChairMaterial(), chairsSearchOptions.getStartPrice(),
                chairsSearchOptions.getEndPrice(), pageRequest);
    }

    public Page<Furniture> getSpecificWardrobes(Integer page, WardrobesSearchOptions wardrobesSearchOptions) {
        if (page < 0) {
            throw new IllegalArgumentException("Page number is incorrect");
        }
        PageRequest pageRequest = PageRequest.of(page, PAGE_RESULT_SIZE, getProperSortOption(wardrobesSearchOptions.getSortOption()));
        return furnitureRepository.findSpecificWardrobes(wardrobesSearchOptions.getTitle(), wardrobesSearchOptions.getColor(),
                wardrobesSearchOptions.getWardrobeMaterial(), wardrobesSearchOptions.getStartPrice(),
                wardrobesSearchOptions.getEndPrice(), pageRequest);
    }

    private Sort getProperSortOption(SortOption sortOption) {
        if (sortOption == null)
            return Sort.unsorted();
        switch (sortOption) {
            case PRICE_ASC -> {
                return Sort.by("f.price").ascending();
            }
            case PRICE_DESC -> {
                return Sort.by("f.price").descending();
            }
            default -> throw new IllegalArgumentException("Not supported sort option");
        }
    }
}
