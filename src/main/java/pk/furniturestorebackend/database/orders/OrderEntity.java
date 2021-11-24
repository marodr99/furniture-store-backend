package pk.furniturestorebackend.database.orders;

import lombok.Data;
import pk.furniturestorebackend.database.furniture.Furniture;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String houseNumber;
    @Column
    private String flatNumber;
    @Column
    private String street;
    @Column(nullable = false)
    private String postalCode;
    @Column(nullable = false)
    private String orderEmail;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "furniture_id")
    private Furniture furniture;
}
