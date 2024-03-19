package lk.project.riyapola.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer vehicleNo;
    private String brand;
    private String model;
    private String description;
    private double price;
    private String vehicleImage;

    public Maintenance(Integer vehicleNo, String brand, String model, String description, double price, String vehicleImage) {
        this.vehicleNo = vehicleNo;
        this.brand = brand;
        this.model = model;
        this.description = description;
        this.price = price;
        this.vehicleImage = vehicleImage;
    }

    public Maintenance(Integer id, Integer vehicleNo, String brand, String model, String description, double price) {
        this.id = id;
        this.vehicleNo = vehicleNo;
        this.brand = brand;
        this.model = model;
        this.description = description;
        this.price = price;
    }

    public Maintenance(Integer vehicleNo, String brand, String model, String description, double price) {
        this.vehicleNo = vehicleNo;
        this.brand = brand;
        this.model = model;
        this.description = description;
        this.price = price;
    }
}
