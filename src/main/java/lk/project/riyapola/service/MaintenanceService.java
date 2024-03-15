package lk.project.riyapola.service;

import lk.project.riyapola.dto.MaintenanceDto;
import lk.project.riyapola.entity.Maintenance;
import lk.project.riyapola.repo.MaintenanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintenanceService {

    private final MaintenanceRepo maintenanceRepo;

    @Autowired
    public MaintenanceService(MaintenanceRepo maintenanceRepo) {
        this.maintenanceRepo = maintenanceRepo;
    }

    public Maintenance saveVehicle(MaintenanceDto maintenanceDto){
        return maintenanceRepo.save(new Maintenance(maintenanceDto.getVehicleNo(),
                maintenanceDto.getBrand(),maintenanceDto.getModel(),maintenanceDto.getDescription(),
                maintenanceDto.getPrice()));
    }

    public List<Maintenance> getAllVehicle(){
        return maintenanceRepo.findAll();
    }

    public Maintenance updateVehicle(Integer id, MaintenanceDto maintenanceDto){
        if (maintenanceRepo.existsById(id)){
            return maintenanceRepo.save(new Maintenance(id,maintenanceDto.getVehicleNo(),
                    maintenanceDto.getBrand(),maintenanceDto.getModel(),maintenanceDto.getDescription(),
                    maintenanceDto.getPrice()));
        }
        return null;
    }

    public String deleteVehicle(Integer id) {
        if (maintenanceRepo.existsById(id)){
            maintenanceRepo.deleteById(id);
            return "Vehicle Deleted";
        }
    return "No Vehicle Found";
    }



}
