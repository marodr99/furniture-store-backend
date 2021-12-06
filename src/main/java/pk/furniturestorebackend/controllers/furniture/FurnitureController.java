package pk.furniturestorebackend.controllers.furniture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pk.furniturestorebackend.chairs.ChairsSearchOptions;
import pk.furniturestorebackend.database.furniture.Furniture;
import pk.furniturestorebackend.database.furniture.FurnitureType;
import pk.furniturestorebackend.furniture.FurnitureEditRequest;
import pk.furniturestorebackend.furniture.FurnitureResponse;
import pk.furniturestorebackend.furniture.FurnitureService;
import pk.furniturestorebackend.wardrobes.WardrobesSearchOptions;

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

    @PostMapping("/wardrobes/specific/{page}")
    public ResponseEntity<FurnitureResponse> getSpecificWardrobes(
            @PathVariable Integer page,
            @RequestBody WardrobesSearchOptions wardrobesSearchOptions) {
        Page<Furniture> specificChairs = furnitureService.getSpecificWardrobes(page, wardrobesSearchOptions);
        FurnitureResponse furnitureResponse = new FurnitureResponse(specificChairs.getTotalPages(), specificChairs.getContent());
        return ResponseEntity.ok(furnitureResponse);
    }

    @PutMapping("/edit")
    public ResponseEntity<Void> editFurniture(@RequestBody FurnitureEditRequest furnitureEditRequest) {
        furnitureService.editFurniture(furnitureEditRequest);
        return ResponseEntity.ok().build();
    }
}
