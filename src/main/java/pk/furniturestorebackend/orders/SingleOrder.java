package pk.furniturestorebackend.orders;

import lombok.Data;
import pk.furniturestorebackend.database.furniture.FurnitureType;

import java.time.LocalDate;

@Data
public class SingleOrder {
    private Integer orderId;
    private Integer furnitureId;
    private String title;
    private FurnitureType furnitureType;
    private LocalDate date;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String houseNumber;
    private String flatNumber;
    private String street;
    private String postalCode;
    private String city;

    public SingleOrder(Integer orderId, Integer furnitureId, String title, FurnitureType furnitureType, LocalDate date,
                       String name, String surname, String email, String phoneNumber, String houseNumber,
                       String flatNumber, String street, String postalCode, String city) {
        this.orderId = orderId;
        this.furnitureId = furnitureId;
        this.title = title;
        this.furnitureType = furnitureType;
        this.date = date;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.houseNumber = houseNumber;
        this.flatNumber = flatNumber;
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
    }
}
