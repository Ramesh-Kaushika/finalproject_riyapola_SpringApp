package lk.project.riyapola.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MaintenanceDto {
    private Integer id;
    private Integer vehicleNo;
    private String brand;
    private String model;
    private String description;
    private double price;
    private String vehicleImage;


}
