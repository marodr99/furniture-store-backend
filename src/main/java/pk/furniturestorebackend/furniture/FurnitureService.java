package pk.furniturestorebackend.furniture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pk.furniturestorebackend.chairs.ChairsSearchOptions;
import pk.furniturestorebackend.database.furniture.Furniture;
import pk.furniturestorebackend.database.furniture.FurnitureRepository;
import pk.furniturestorebackend.database.furniture.FurnitureType;
import pk.furniturestorebackend.database.furniture.SortOption;
import pk.furniturestorebackend.database.furniture.chairs.Chair;
import pk.furniturestorebackend.database.furniture.chairs.ChairMaterial;
import pk.furniturestorebackend.database.furniture.chairs.ChairRepository;
import pk.furniturestorebackend.database.furniture.wardrobes.Wardrobe;
import pk.furniturestorebackend.database.furniture.wardrobes.WardrobeMaterial;
import pk.furniturestorebackend.database.furniture.wardrobes.WardrobeRepository;
import pk.furniturestorebackend.wardrobes.WardrobesSearchOptions;

@Service
public class FurnitureService {
    private static final int PAGE_RESULT_SIZE = 30;
    private final FurnitureRepository furnitureRepository;
    private final ChairRepository chairRepository;
    private final WardrobeRepository wardrobeRepository;

    @Autowired
    public FurnitureService(FurnitureRepository furnitureRepository,
                            ChairRepository chairRepository,
                            WardrobeRepository wardrobeRepository) {
        this.furnitureRepository = furnitureRepository;
        this.chairRepository = chairRepository;
        this.wardrobeRepository = wardrobeRepository;
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

    @Transactional
    public void editFurniture(FurnitureEditRequest furnitureEditRequest) {
        Integer id = furnitureEditRequest.getId();
        Furniture furniture = furnitureRepository.findById(id).orElse(new Furniture());
        updateFurniture(furniture, furnitureEditRequest);
        String requestFurnitureType = furnitureEditRequest.getFurnitureType();

        if (requestFurnitureType.equalsIgnoreCase(FurnitureType.CHAIRS.name())) {
            Chair chair = chairRepository.findById(id).orElse(new Chair());
            updateChair(chair, furnitureEditRequest, furniture);
        } else if (requestFurnitureType.equalsIgnoreCase(FurnitureType.WARDROBES.name())) {
            Wardrobe wardrobe = wardrobeRepository.findById(id).orElse(new Wardrobe());
            updateWardrobe(wardrobe, furnitureEditRequest, furniture);
            wardrobeRepository.save(wardrobe);
        }
    }

    private void updateWardrobe(Wardrobe wardrobe, FurnitureEditRequest furnitureEditRequest, Furniture furniture) {
        wardrobe.setFurniture_id(furnitureEditRequest.getId());
        wardrobe.setWidth(furnitureEditRequest.getWidth());
        wardrobe.setHeight(furnitureEditRequest.getHeight());
        wardrobe.setDepth(furnitureEditRequest.getDepth());
        wardrobe.setColor(furnitureEditRequest.getColor());
        wardrobe.setMaterial(WardrobeMaterial.valueOf(furnitureEditRequest.getMaterial().toUpperCase()));
        wardrobe.setAdditionalInformation(furnitureEditRequest.getAdditionalInformation());
        wardrobe.setStock(furnitureEditRequest.getStock());
        wardrobe.setFileName(furnitureEditRequest.getFileName());
        wardrobe.setImagesUrl(furnitureEditRequest.getImagesUrl());
        wardrobe.setFurniture(furniture);
    }

    private void updateChair(Chair chair, FurnitureEditRequest furnitureEditRequest, Furniture furniture) {
        chair.setId(furnitureEditRequest.getId());
        chair.setMaxWeight(furnitureEditRequest.getMaxWeight());
        chair.setWidth(furnitureEditRequest.getWidth());
        chair.setHeight(furnitureEditRequest.getHeight());
        chair.setDepth(furnitureEditRequest.getDepth());
        chair.setColor(furnitureEditRequest.getColor());
        chair.setMaterial(ChairMaterial.valueOf(furnitureEditRequest.getMaterial().toUpperCase()));
        chair.setAdditionalInformation(chair.getAdditionalInformation());
        chair.setStock(furnitureEditRequest.getStock());
        chair.setFileName(furnitureEditRequest.getFileName());
        chair.setImagesUrl(furnitureEditRequest.getImagesUrl());
        chair.setFurniture(furniture);
    }

    private void updateFurniture(Furniture furniture, FurnitureEditRequest furnitureEditRequest) {
        furniture.setId(furnitureEditRequest.getId());
        furniture.setTitle(furnitureEditRequest.getTitle());
        furniture.setPrice(furnitureEditRequest.getPrice());
        furniture.setImgUrl(furnitureEditRequest.getImgUrl());
        furniture.setFurnitureType(FurnitureType.valueOf(furnitureEditRequest.getFurnitureType()));
    }

}
