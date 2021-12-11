package pk.furniturestorebackend.orders;

import lombok.Data;
import pk.furniturestorebackend.database.furniture.FurnitureType;

import java.time.LocalDate;

@Data
public class FullOrderInfo {
    private Integer orderId;
    private String email;
    private String name;
    private String surname;
    private String phoneNumber;
    private String houseNumber;
    private String flatNumber;
    private String street;
    private String postalCode;
    private String orderEmail;
    private String city;
    private LocalDate date;
    private Integer furnitureId;
    private String title;
    private double price;
    private String imgUrl;
    private FurnitureType furnitureType;
}
