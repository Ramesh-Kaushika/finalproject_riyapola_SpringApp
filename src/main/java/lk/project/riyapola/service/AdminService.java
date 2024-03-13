package lk.project.riyapola.service;

import lk.project.riyapola.dto.AdminDto;
import lk.project.riyapola.entity.Admin;
import lk.project.riyapola.repo.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final AdminRepo adminRepo;

     @Autowired
    public AdminService(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }

    public Admin saveAdmin(AdminDto adminDto){
         return adminRepo.save(new Admin(adminDto.getUserName(),adminDto.getEmail(),adminDto.getPassword()));
    }
    public List<Admin> getAllCustomers(){
        return adminRepo.findAll();
    }
  //  public void deleteCustomer(){}
   // public void updateCustomer(){}
}
