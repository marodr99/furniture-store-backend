package pk.furniturestorebackend.controllers.furniture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pk.furniturestorebackend.database.furniture.Furniture;
import pk.furniturestorebackend.database.furniture.FurnitureType;
import pk.furniturestorebackend.furniture.FurnitureResponse;
import pk.furniturestorebackend.furniture.FurnitureService;

@RestController
@RequestMapping("/furniture")
public class FurnitureController {
    private final FurnitureService furnitureService;

    @Autowired
    public FurnitureController(FurnitureService furnitureService) {
        this.furnitureService = furnitureService;
    }

    @GetMapping("/{furnitureType}/{page}")
    public ResponseEntity<FurnitureResponse> getAllFurniture(
            @PathVariable String furnitureType, @PathVariable Integer page) {
        Page<Furniture> furniture = furnitureService
                .getAllFurniture(FurnitureType.valueOf(furnitureType.toUpperCase()), page);
        FurnitureResponse furnitureResponse = new FurnitureResponse(furniture.getTotalPages(), furniture.getContent());
        return ResponseEntity.ok(furnitureResponse);
    }
}
