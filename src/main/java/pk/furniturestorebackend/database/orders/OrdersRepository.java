package pk.furniturestorebackend.database.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pk.furniturestorebackend.orders.Order;
import pk.furniturestorebackend.orders.SingleOrder;
import pk.furniturestorebackend.reports.ReportDetailsResponse;

import java.time.LocalDate;
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

    @Query(nativeQuery = true, value = """
            SELECT f.id                      as furnitureId,
                   'The most sold furniture' as description,
                   furniture_type            as furnitureType,
                   title                     as name,
                   COUNT(*)                  as totalSold,
                   SUM(price)                as profit
            FROM orders
                     JOIN furnitures f on orders.furniture_id = f.id
            WHERE DATE(date) >= :startDate AND DATE(date) <= :endDate
            GROUP BY id
            ORDER BY totalSold DESC
            limit 1;
            """)
    ReportDetailsResponse getTheMostSoldFurniture(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(nativeQuery = true, value = """
            SELECT f.id                     as furnitureId,
                   'The most popular chair' as description,
                   furniture_type           as furnitureType,
                   title                    as name,
                   COUNT(*)                 as totalSold,
                   SUM(price)               as profit
            FROM orders
                     JOIN furnitures f on orders.furniture_id = f.id
            WHERE furniture_type = 'CHAIRS' AND DATE(date) >= :startDate AND DATE(date) <= :endDate
            GROUP BY id ORDER BY totalSold desc LIMIT 1;
            """)
    ReportDetailsResponse getTheMostPopularChair(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(nativeQuery = true, value = """
            SELECT f.id                     as furnitureId,
                   'The most popular wardrobe' as description,
                   furniture_type           as furnitureType,
                   title                    as name,
                   COUNT(*)                 as totalSold,
                   SUM(price)               as profit
            FROM orders
                     JOIN furnitures f on orders.furniture_id = f.id
            WHERE furniture_type = 'WARDROBES' AND DATE(date) >= :startDate AND DATE(date) <= :endDate
            GROUP BY id ORDER BY totalSold desc LIMIT 1;
            """)
    ReportDetailsResponse getTheMostPopularWardrobe(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(nativeQuery = true, value = """
            SELECT 0                 as furnitureId,
                   'All sold chairs' as description,
                   furniture_type    as furnitureType,
                   '*'               as name,
                   COUNT(*)          as totalSold,
                   SUM(price)        as profit
            FROM orders
                     JOIN furnitures f on orders.furniture_id = f.id
            WHERE furniture_type = 'CHAIRS' AND DATE(date) >= :startDate AND DATE(date) <= :endDate
            GROUP BY furniture_type;
            """)
    ReportDetailsResponse getAllSoldChairs(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(nativeQuery = true, value = """
            SELECT 0                 as furnitureId,
                   'All sold wardrobes' as description,
                   furniture_type    as furnitureType,
                   '*'               as name,
                   COUNT(*)          as totalSold,
                   SUM(price)        as profit
            FROM orders
                     JOIN furnitures f on orders.furniture_id = f.id
            WHERE furniture_type = 'WARDROBES' AND DATE(date) >= :startDate AND DATE(date) <= :endDate
            GROUP BY furniture_type;
            """)
    ReportDetailsResponse getAllSoldWardrobes(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
