package lk.project.riyapola.service;
import lk.project.riyapola.dto.AdminDto;
import lk.project.riyapola.entity.Admin;
import lk.project.riyapola.repo.AdminRepo;
import lk.project.riyapola.util.JwtTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminService {

    private final AdminRepo adminRepo;
    private final JwtTokenGenerator jwtTokenGenerator;

     @Autowired
    public AdminService(AdminRepo adminRepo, JwtTokenGenerator jwtTokenGenerator) {
        this.adminRepo = adminRepo;
         this.jwtTokenGenerator = jwtTokenGenerator;
     }

    public Admin registerAdmin(AdminDto adminDto){

        String originalInput = adminDto.getPassword();
        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
         return adminRepo.save(new Admin(adminDto.getUserName(),adminDto.getEmail(),encodedString));
    }
    public Map<String,String> loginAdmin(AdminDto adminDto){
            HashMap<String,String> response = new HashMap<>();


        String originalInput = adminDto.getPassword();
        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());

//        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
//        String decodedString = new String(decodedBytes);


        Admin userByEmailAndPassword = adminRepo.findUserByEmailAndPassword(adminDto.getEmail(),encodedString);

        if (userByEmailAndPassword != null) {
            String token = this.jwtTokenGenerator.generateJwtToken(userByEmailAndPassword);
            response.put("token", token);
        } else {
            response.put("massage", "wrong Credentials");
        }
        return response;

    }
    public List<Admin> getAllAdmin(){
         return adminRepo.findAll();
    }
    public Admin updateAdmin(Integer id,AdminDto adminDto){
         if (adminRepo.existsById(id)){
             return adminRepo.save(new Admin(id,adminDto.getUserName(),adminDto.getEmail(),adminDto.getPassword()));
         }
         return null;
    }

    public String deleteAdmin(Integer id){

         if (adminRepo.existsById(id)){
             adminRepo.deleteById(id);
             return "Admin Deleted";
         }
         return "No Customer Found";
    }

    public Object searchAdmin(Integer id) {
        if(adminRepo.existsById(id)){
            Admin admin = adminRepo.findById(id).get();

            return admin;
        }

        return  "ID Not Found";
    }

        public Object searchAdminName(String userName){
            return adminRepo.findAdminByUserName(userName);

    }



}
