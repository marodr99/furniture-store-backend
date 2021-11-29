package pk.furniturestorebackend.orders;

import lombok.Data;
import pk.furniturestorebackend.database.furniture.FurnitureType;

import java.time.LocalDate;

@Data
public class Order {
    private Integer orderId;
    private String title;
    private FurnitureType furnitureType;
    private LocalDate date;

    public Order(Integer orderId, String title, FurnitureType furnitureType, LocalDate date) {
        this.orderId = orderId;
        this.title = title;
        this.furnitureType = furnitureType;
        this.date = date;
    }
}
