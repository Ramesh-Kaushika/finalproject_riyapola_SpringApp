package lk.project.riyapola.controller;

import lk.project.riyapola.dto.AdminDto;
import lk.project.riyapola.entity.Admin;
import lk.project.riyapola.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;


    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;

    }

    @PostMapping("/register")
    public ResponseEntity<Admin> registerAdmin(@RequestBody AdminDto admin) {
        Admin ad = adminService.registerAdmin(admin);
        return new ResponseEntity<>(ad, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginAdmin(@RequestBody AdminDto adminDto) {
        Map<String, String> stringStringHashMap = adminService.loginAdmin(adminDto);
        return new ResponseEntity<>(stringStringHashMap, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> allAdmin = adminService.getAllAdmin();
        return new ResponseEntity<>(allAdmin, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Integer id, @RequestBody AdminDto adminDto) {
        Admin admin = adminService.updateAdmin(id, adminDto);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable Integer id) {
        String deleted = adminService.deleteAdmin(id);
        return new ResponseEntity<>(deleted,HttpStatus.CREATED);

    }

    @GetMapping("/search_admin/{id}")
    public ResponseEntity<Object> searchAdmin(@PathVariable Integer id){
        Object admin = adminService.searchAdmin(id);
        return new ResponseEntity<>(admin,HttpStatus.OK);
    }

    @GetMapping("/search_admin_name/{userName}")
    public ResponseEntity<Object>searchAdminName(@PathVariable String userName){
        Object admin = adminService.searchAdminName(userName);
        return new ResponseEntity<>(admin,HttpStatus.OK);

    }

}
