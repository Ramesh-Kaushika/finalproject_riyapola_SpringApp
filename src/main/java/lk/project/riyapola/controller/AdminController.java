package lk.project.riyapola.controller;
import lk.project.riyapola.dto.AdminDto;
import lk.project.riyapola.entity.Admin;
import lk.project.riyapola.service.AdminService;
import lk.project.riyapola.util.JwtTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173/")
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
    public ResponseEntity<Object> registerAdmin(@RequestBody AdminDto admin) {
        Object ad = adminService.registerAdmin(admin);
        return new ResponseEntity<>(ad, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginAdmin(@RequestBody AdminDto adminDto) {
        return adminService.loginAdmin(adminDto);
    }

    @GetMapping
    public ResponseEntity<Object> getAllAdmins(@RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            List<Admin> allAdmin = adminService.getAllAdmin();
            return new ResponseEntity<>(allAdmin, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("invalid Token", HttpStatus.FORBIDDEN);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAdmin(@RequestHeader(name = "Authorization") String authorizationHeader, @PathVariable Integer id, @RequestBody AdminDto adminDto) {
        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            Object admin = adminService.updateAdmin(id, adminDto);
            return new ResponseEntity<>(admin, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("invalid Token", HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdmin(@RequestHeader(name = "Authorization") String authorizationHeader, @PathVariable Integer id) {
        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
        String deleted = adminService.deleteAdmin(id);
        return new ResponseEntity<>(deleted, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>("invalid Token", HttpStatus.FORBIDDEN);
        }
    }

}
