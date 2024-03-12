package lk.project.riyapola.controller;
import lk.project.riyapola.entity.Admin;
import lk.project.riyapola.repo.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminRepo adminRepo;

    @Autowired
    public AdminController(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }

    @PostMapping
    public ResponseEntity<Admin> saveAdmin(@RequestBody Admin admin){
        Admin save = adminRepo.save(admin);

        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Admin>> getAdmin(){
        List<Admin> all = adminRepo.findAll();
        return new ResponseEntity<>(all,HttpStatus.OK);
    }

}
