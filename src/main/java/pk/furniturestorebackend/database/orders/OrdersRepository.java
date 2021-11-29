package pk.furniturestorebackend.database.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pk.furniturestorebackend.orders.Order;
import pk.furniturestorebackend.orders.SingleOrder;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<OrderEntity, Integer> {
    @Query("SELECT new pk.furniturestorebackend.orders.Order(oe.orderId, f.title, f.furnitureType, oe.date) " +
            "FROM OrderEntity oe JOIN oe.furniture f WHERE oe.email = (:email) ORDER BY oe.date DESC ")
    List<Order> findAllByEmailOrderByDateDesc(String email);

    @Query("SELECT new pk.furniturestorebackend.orders.SingleOrder(oe.orderId, f.id, f.title, f.furnitureType, oe.date, " +
            "oe.name, oe.surname, oe.orderEmail, oe.phoneNumber, oe.houseNumber, oe.flatNumber, oe.street, oe.postalCode, " +
            "oe.city) FROM OrderEntity oe JOIN oe.furniture f WHERE oe.email = (:email) AND oe.orderId = (:orderId)")
    SingleOrder findByOrderIdAndEmail(Integer orderId, String email);
}
