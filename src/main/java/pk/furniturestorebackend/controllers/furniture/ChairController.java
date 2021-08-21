package pk.furniturestorebackend.controllers.furniture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pk.furniturestorebackend.database.furniture.chairs.Chair;
import pk.furniturestorebackend.database.furniture.chairs.ChairService;

import java.util.List;

@RestController
@RequestMapping("/furniture/chairs")
public class ChairController {
    private final ChairService chairService;

    @Autowired
    public ChairController(ChairService chairService) {
        this.chairService = chairService;
    }

    @GetMapping
    public ResponseEntity<List<Chair>> getAllFurniture() {
        List<Chair> chairs = chairService.getAllChairs();
        return ResponseEntity.ok(chairs);
    }
}
