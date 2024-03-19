package lk.project.riyapola.service;

import lk.project.riyapola.dto.MaintenanceDto;
import lk.project.riyapola.entity.Maintenance;
import lk.project.riyapola.repo.MaintenanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class MaintenanceService {

    private final MaintenanceRepo maintenanceRepo;
    public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/assets";

    @Autowired
    public MaintenanceService(MaintenanceRepo maintenanceRepo) {
        this.maintenanceRepo = maintenanceRepo;
    }

    public Maintenance saveVehicle(MaintenanceDto maintenanceDto, MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        Path path = Paths.get(uploadDirectory, originalFilename);
        Files.write(path, file.getBytes());
        return maintenanceRepo.save(new Maintenance(maintenanceDto.getVehicleNo(), maintenanceDto.getBrand(), maintenanceDto.getModel(), maintenanceDto.getDescription(), maintenanceDto.getPrice(), originalFilename));
    }

    public List<Maintenance> getAllVehicle() {
        return maintenanceRepo.findAll();
    }

    public Maintenance updateVehicle(Integer id, MaintenanceDto maintenanceDto) {
        if (maintenanceRepo.existsById(id)) {
            return maintenanceRepo.save(new Maintenance(id, maintenanceDto.getVehicleNo(), maintenanceDto.getBrand(), maintenanceDto.getModel(), maintenanceDto.getDescription(), maintenanceDto.getPrice()));
        }
        return null;
    }

    public String deleteVehicle(Integer id) {
        if (maintenanceRepo.existsById(id)) {
            maintenanceRepo.deleteById(id);
            return "Vehicle Deleted";
        }
        return "No Vehicle Found";
    }


}
