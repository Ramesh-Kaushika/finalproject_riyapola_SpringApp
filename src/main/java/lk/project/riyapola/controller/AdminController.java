package lk.project.riyapola.controller;
import lk.project.riyapola.dto.AdminDto;
import lk.project.riyapola.entity.Admin;
import lk.project.riyapola.entity.Customer;
import lk.project.riyapola.repo.AdminRepo;
import lk.project.riyapola.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;

    }

    @PostMapping
    public ResponseEntity<Admin> saveAdmin(@RequestBody AdminDto admin){
        Admin ad = adminService.saveAdmin(admin);
        return new ResponseEntity<>(ad, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Admin>> getAdmin(){
        List<Admin> all = adminService.getAllCustomers();
        return new ResponseEntity<>(all,HttpStatus.OK);
    }

}
