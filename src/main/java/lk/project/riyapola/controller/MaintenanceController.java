package lk.project.riyapola.controller;

import lk.project.riyapola.dto.MaintenanceDto;
import lk.project.riyapola.entity.Maintenance;
import lk.project.riyapola.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5174/")
@RestController
@RequestMapping("/vehicle")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    @Autowired
    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @PostMapping
    public ResponseEntity<Maintenance> saveVehicle(@RequestBody MaintenanceDto maintenanceDto){
        Maintenance maintenance = maintenanceService.saveVehicle(maintenanceDto);
        return new ResponseEntity<>(maintenance, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Maintenance>> getAllVehicle(){
        List<Maintenance> allVehicle = maintenanceService.getAllVehicle();
        return new ResponseEntity<>(allVehicle, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Maintenance> updateVehicle(@PathVariable Integer id, @RequestBody MaintenanceDto maintenanceDto){
        Maintenance maintenance = maintenanceService.updateVehicle(id, maintenanceDto);
        return new ResponseEntity<>(maintenance, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Integer id){
        String s = maintenanceService.deleteVehicle(id);
        return new ResponseEntity<>(s, HttpStatus.CREATED);
    }
}
