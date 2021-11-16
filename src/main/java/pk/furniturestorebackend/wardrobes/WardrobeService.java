package pk.furniturestorebackend.wardrobes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pk.furniturestorebackend.database.furniture.wardrobes.Wardrobe;
import pk.furniturestorebackend.database.furniture.wardrobes.WardrobeRepository;
import pk.furniturestorebackend.exceptions.NotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WardrobeService {
    private final WardrobeRepository wardrobeRepository;

    @Autowired
    public WardrobeService(WardrobeRepository wardrobeRepository) {
        this.wardrobeRepository = wardrobeRepository;
    }

    public Wardrobe getWardrobe(Integer id) {
        if (id < 0) {
            throw new IllegalArgumentException("Id can not be less than 0");
        }
        return wardrobeRepository.findById(id).orElseThrow(() -> new NotFoundException("Wardrobe with id " + id + " not found"));
    }

    public Map<String, List<String>> getWardrobesSearchOptions() {
        Map<String, List<String>> possibleSearchOptions = new HashMap<>();
        List<String> colors = wardrobeRepository.getAllColors();
        possibleSearchOptions.put("color", colors);
        List<String> materials = wardrobeRepository.getAllMaterials();
        possibleSearchOptions.put("material", materials);
        return possibleSearchOptions;
    }
}
