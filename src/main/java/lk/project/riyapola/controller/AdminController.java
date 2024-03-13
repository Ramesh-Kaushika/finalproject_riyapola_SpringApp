package lk.project.riyapola.controller;

import lk.project.riyapola.dto.AdminDto;
import lk.project.riyapola.entity.Admin;
import lk.project.riyapola.service.AdminService;
import lk.project.riyapola.util.JwtTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

private final AdminService adminService;
private final JwtTokenGenerator jwtTokenGenerator;

    @Autowired
    public AdminController(AdminService adminService, JwtTokenGenerator jwtTokenGenerator) {
        this.adminService = adminService;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<Admin> registerAdmin(@RequestBody AdminDto admin){
        Admin ad = adminService.registerAdmin(admin);
        return new ResponseEntity<>(ad, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public Map<String, String> loginAdmin(@RequestBody AdminDto adminDto){
       Map<String, String> response = new HashMap<>();

        Admin admin = adminService.loginAdmin(adminDto.getEmail(), adminDto.getPassword());

        if (admin != null) {
            String token = this.jwtTokenGenerator.generateJwtToken(admin);
            response.put("token", token);
        } else {
            response.put("massage", "wrong Credentials");
        }
        return response;

    }

}
