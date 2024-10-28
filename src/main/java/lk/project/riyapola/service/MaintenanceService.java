package lk.project.riyapola.service;

import lk.project.riyapola.dto.ImageDetailsGetDto;
import lk.project.riyapola.dto.MaintenanceDto;
import lk.project.riyapola.entity.Maintenance;
import lk.project.riyapola.repo.MaintenanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class MaintenanceService {

    private final MaintenanceRepo maintenanceRepo;
    public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/assets";

    @Autowired
    public MaintenanceService(MaintenanceRepo maintenanceRepo) {
        this.maintenanceRepo = maintenanceRepo;
    }

    public ImageDetailsGetDto saveVehicle(MaintenanceDto maintenanceDto) throws IOException, URISyntaxException {

        String absolutePath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getAbsolutePath();
        File file = new File(absolutePath + "/src/main/resources/assets");
        file.mkdir();

        maintenanceDto.getVehicleImage().transferTo(new File(file.getAbsolutePath() + "/" + maintenanceDto.getVehicleImage().getOriginalFilename()));

        Maintenance maintenance = new Maintenance(maintenanceDto.getVehicleNo(), maintenanceDto.getBrand(), maintenanceDto.getModel(), maintenanceDto.getDescription(), maintenanceDto.getPrice(), maintenanceDto.getVehicleImage().getOriginalFilename());
        maintenance.setVehicleImage("assets/" +maintenanceDto.getVehicleImage().getOriginalFilename());


        Maintenance maintenanceNew = maintenanceRepo.save(maintenance);
        System.out.println(maintenanceNew);

//     return (save);
        return new ImageDetailsGetDto(maintenanceNew.getId(),maintenanceNew.getVehicleNo(), maintenanceNew.getBrand(),maintenanceNew.getModel(), maintenanceNew.getDescription(), maintenanceNew.getPrice(), maintenanceNew.getVehicleImage());
//        String originalFilename = file.getOriginalFilename();
//        Path path = Paths.get(uploadDirectory, originalFilename);
//        Files.write(path, file.getBytes());
//        maintenanceDto.setVehicleImage(originalFilename);
//
//        return maintenanceRepo.save(new Maintenance(maintenanceDto.getVehicleNo(), maintenanceDto.getBrand(), maintenanceDto.getModel(), maintenanceDto.getDescription(), maintenanceDto.getPrice(), maintenanceDto.getVehicleImage()));
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
