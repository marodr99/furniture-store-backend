package pk.furniturestorebackend.controllers.furniture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pk.furniturestorebackend.chairs.ChairService;
import pk.furniturestorebackend.chairs.SingleChairResponse;
import pk.furniturestorebackend.database.furniture.chairs.Chair;

@RestController
@RequestMapping("/chairs")
public class ChairController {
    private final ChairService chairService;

    @Autowired
    public ChairController(ChairService chairService) {
        this.chairService = chairService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SingleChairResponse> getChair(@PathVariable Integer id) {
        Chair chair = chairService.getChair(id);
        SingleChairResponse singleChairResponse = new SingleChairResponse(chair);
        return ResponseEntity.ok(singleChairResponse);
    }
}
