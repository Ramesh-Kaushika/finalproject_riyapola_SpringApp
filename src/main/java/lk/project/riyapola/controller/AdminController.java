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
    public ResponseEntity<List<Object>> getAllAdmins(@RequestHeader(name = "Authorization") String authorizationHeader) {
        List<Object> allAdmin = adminService.getAllAdmin(authorizationHeader);
        return new ResponseEntity<>(allAdmin, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAdmin(@RequestHeader(name = "Authorization") String authorizationHeader, @PathVariable Integer id, @RequestBody AdminDto adminDto) {
        Object admin = adminService.updateAdmin(id, adminDto, authorizationHeader);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdmin(@RequestHeader(name = "Authorization") String authorizationHeader,@PathVariable Integer id) {
        String deleted = adminService.deleteAdmin(id,authorizationHeader);
        return new ResponseEntity<>(deleted,HttpStatus.CREATED);

    }

    @GetMapping("/search_admin/{id}")
    public ResponseEntity<Object> searchAdmin(@RequestHeader(name = "Authorization") String authorizationHeader,@PathVariable Integer id){
        Object admin = adminService.searchAdmin(id, authorizationHeader);
        return new ResponseEntity<>(admin,HttpStatus.OK);
    }

    @GetMapping("/search_admin_name/{userName}")
    public ResponseEntity<List<Object>>searchAdminName(@RequestHeader(name = "Authorization") String authorizationHeader,@PathVariable String userName){
        List<Object> admin = adminService.searchAdminName(userName,authorizationHeader);
        return new ResponseEntity<>(admin,HttpStatus.OK);

    }

}
