package pk.furniturestorebackend.database.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pk.furniturestorebackend.orders.Order;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<OrderEntity, Integer> {
    @Query("SELECT new pk.furniturestorebackend.orders.Order(oe.orderId, f.title, f.furnitureType, oe.date) FROM OrderEntity oe JOIN oe.furniture f ORDER BY oe.date DESC ")
    List<Order> findAllByEmailOrderByDateDesc(String email);
}
