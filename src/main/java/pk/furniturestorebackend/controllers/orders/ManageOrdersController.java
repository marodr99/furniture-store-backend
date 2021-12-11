package pk.furniturestorebackend.controllers.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pk.furniturestorebackend.orders.FullOrderInfo;
import pk.furniturestorebackend.orders.OrdersService;

import java.util.List;

@RestController
@RequestMapping("/manage")
public class ManageOrdersController {
    private final OrdersService ordersService;

    @Autowired
    public ManageOrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping("/orders")
    public ResponseEntity<List<FullOrderInfo>> getOrders() {
        List<FullOrderInfo> allOrders = ordersService.getAllOrders();
        return ResponseEntity.ok(allOrders);
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<Void> modifyOrder(@PathVariable Integer id, @RequestBody FullOrderInfo fullOrderInfo) {
        ordersService.modifyOrder(id, fullOrderInfo);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id) {
        ordersService.deleteOrder(id);
        return ResponseEntity.ok().build();
    }
}
