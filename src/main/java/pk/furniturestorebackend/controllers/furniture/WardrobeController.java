package pk.furniturestorebackend.controllers.furniture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pk.furniturestorebackend.database.furniture.wardrobes.Wardrobe;
import pk.furniturestorebackend.wardrobes.SingleWardrobeResponse;
import pk.furniturestorebackend.wardrobes.WardrobeService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wardrobes")
public class WardrobeController {
    private final WardrobeService wardrobeService;

    @Autowired
    public WardrobeController(WardrobeService wardrobeService) {
        this.wardrobeService = wardrobeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SingleWardrobeResponse> getWardrobe(@PathVariable Integer id) {
        Wardrobe wardrobe = wardrobeService.getWardrobe(id);
        SingleWardrobeResponse singleWardrobeResponse = new SingleWardrobeResponse(wardrobe);
        return ResponseEntity.ok(singleWardrobeResponse);
    }

    @GetMapping("/search/options")
    public ResponseEntity<Map<String, List<String>>> getWardrobesSearchOptions() {
        Map<String, List<String>> wardrobesSearchOptions = wardrobeService.getWardrobesSearchOptions();
        return ResponseEntity.ok(wardrobesSearchOptions);
    }
}
