package pk.furniturestorebackend.controllers.furniture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pk.furniturestorebackend.chairs.ChairResponse;
import pk.furniturestorebackend.chairs.ChairService;
import pk.furniturestorebackend.database.furniture.chairs.Chair;

@RestController
@RequestMapping("/furniture/chairs")
public class ChairController {
    private final ChairService chairService;

    @Autowired
    public ChairController(ChairService chairService) {
        this.chairService = chairService;
    }

    @GetMapping("/{page}")
    public ResponseEntity<ChairResponse> getAllFurniture(@PathVariable Integer page) {
        Page<Chair> pageChairs = chairService.getAllChairs(page);
        ChairResponse chairResponse = new ChairResponse(pageChairs.getTotalPages(), pageChairs.getContent());
        return ResponseEntity.ok(chairResponse);
    }
}
