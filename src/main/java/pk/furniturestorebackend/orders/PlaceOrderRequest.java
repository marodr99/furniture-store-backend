package pk.furniturestorebackend.orders;

import lombok.Data;

@Data
public class PlaceOrderRequest {
    private Integer furnitureId;
    private String name;
    private String surname;
    private String phoneNumber;
    private String houseNumber;
    private String flatNumber;
    private String street;
    private String postalCode;
    private String city;
    private String orderEmail;
}
