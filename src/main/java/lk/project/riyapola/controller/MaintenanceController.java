package lk.project.riyapola.controller;

import lk.project.riyapola.dto.ImageDetailsGetDto;
import lk.project.riyapola.dto.MaintenanceDto;
import lk.project.riyapola.entity.Maintenance;
import lk.project.riyapola.service.MaintenanceService;
import lk.project.riyapola.util.JwtTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
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
    public ResponseEntity<Object> saveVehicle(@RequestHeader(name = "Authorization") String authorizationHeader, @ModelAttribute MaintenanceDto maintenanceDto) throws IOException, URISyntaxException {

        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            ImageDetailsGetDto imageDetailsGetDto = maintenanceService.saveVehicle(maintenanceDto);
            return new ResponseEntity<>(imageDetailsGetDto, HttpStatus.CREATED);
      } else {
           return new ResponseEntity<>("invalid Token", HttpStatus.FORBIDDEN);
       }

    }

    @GetMapping
    public ResponseEntity<Object> getAllVehicle() {
       // if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            List<Maintenance> allVehicle = maintenanceService.getAllVehicle();

            return new ResponseEntity<>(allVehicle, HttpStatus.OK);
        //} else {
          //  return new ResponseEntity<>("invalid Token", HttpStatus.FORBIDDEN);
       // }
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
