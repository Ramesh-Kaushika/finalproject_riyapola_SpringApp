package lk.project.riyapola.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class MaintenanceDto {
    private Integer id;
    private Integer vehicleNo;
    private String brand;
    private String model;
    private String description;
    private double price;
    private MultipartFile vehicleImage;


}
