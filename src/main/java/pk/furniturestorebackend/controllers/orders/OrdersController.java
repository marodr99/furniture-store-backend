package pk.furniturestorebackend.controllers.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pk.furniturestorebackend.orders.Order;
import pk.furniturestorebackend.orders.OrdersService;
import pk.furniturestorebackend.orders.PlaceOrderRequest;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersService ordersService;

    @Autowired
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody PlaceOrderRequest orderRequest) {
        ordersService.createOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orders = ordersService.getOrders();
        return ResponseEntity.ok(orders);
    }
}
