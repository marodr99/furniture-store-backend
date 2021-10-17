package pk.furniturestorebackend.controllers.furniture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pk.furniturestorebackend.chairs.ChairsSearchOptions;
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

    @PostMapping("/chairs/specific/{page}")
    public ResponseEntity<FurnitureResponse> getSpecificChairs(
            @PathVariable Integer page,
            @RequestBody ChairsSearchOptions chairsSearchOptions) {
        Page<Furniture> specificChairs = furnitureService.getSpecificChairs(page, chairsSearchOptions);
        FurnitureResponse furnitureResponse = new FurnitureResponse(specificChairs.getTotalPages(), specificChairs.getContent());
        return ResponseEntity.ok(furnitureResponse);
    }
}
