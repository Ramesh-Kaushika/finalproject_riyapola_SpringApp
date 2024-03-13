package lk.project.riyapola.service;

import lk.project.riyapola.dto.AdminDto;
import lk.project.riyapola.entity.Admin;
import lk.project.riyapola.repo.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepo adminRepo;

     @Autowired
    public AdminService(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }

    public Admin registerAdmin(AdminDto adminDto){
         return adminRepo.save(new Admin(adminDto.getUserName(),adminDto.getEmail(),adminDto.getPassword()));
    }
    public Admin loginAdmin(String email, String password){
       return adminRepo.findUserByEmailAndPassword(email,password);
    }
  //  public void deleteCustomer(){}
   // public void updateCustomer(){}
}
