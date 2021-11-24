package pk.furniturestorebackend.controllers.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pk.furniturestorebackend.orders.OrdersService;
import pk.furniturestorebackend.orders.PlaceOrderRequest;

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
}
