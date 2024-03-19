package lk.project.riyapola.controller;

import lk.project.riyapola.dto.MaintenanceDto;
import lk.project.riyapola.entity.Maintenance;
import lk.project.riyapola.service.MaintenanceService;
import lk.project.riyapola.util.JwtTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/vehicle")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;
    private final JwtTokenGenerator jwtTokenGenerator;

    @Autowired
    public MaintenanceController(MaintenanceService maintenanceService, JwtTokenGenerator jwtTokenGenerator) {
        this.maintenanceService = maintenanceService;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @PostMapping
    public ResponseEntity<Object> saveVehicle(@RequestHeader(name = "Authorization") String authorizationHeader, @RequestBody MaintenanceDto maintenanceDto) {
        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            Maintenance maintenance = maintenanceService.saveVehicle(maintenanceDto);
            return new ResponseEntity<>(maintenance, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("invalid Token", HttpStatus.FORBIDDEN);
        }

    }

    @GetMapping
    public ResponseEntity<Object> getAllVehicle(@RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            List<Maintenance> allVehicle = maintenanceService.getAllVehicle();
            return new ResponseEntity<>(allVehicle, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("invalid Token", HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateVehicle(@RequestHeader(name = "Authorization") String authorizationHeader, @PathVariable Integer id, @RequestBody MaintenanceDto maintenanceDto) {
        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            Maintenance maintenance = maintenanceService.updateVehicle(id, maintenanceDto);
            return new ResponseEntity<>(maintenance, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("invalid Token", HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(@RequestHeader(name = "Authorization") String authorizationHeader, @PathVariable Integer id) {
        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            String s = maintenanceService.deleteVehicle(id);
            return new ResponseEntity<>(s, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("invalid Token", HttpStatus.FORBIDDEN);
        }
    }
}
