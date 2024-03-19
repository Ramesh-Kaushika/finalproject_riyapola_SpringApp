package lk.project.riyapola.service;
import lk.project.riyapola.dto.AdminDto;
import lk.project.riyapola.entity.Admin;
import lk.project.riyapola.repo.AdminRepo;
import lk.project.riyapola.util.JwtTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public Object registerAdmin(AdminDto adminDto) {
        if (adminRepo.findUserByEmail(adminDto.getEmail()) == null) {

            String originalInput = adminDto.getPassword();
            String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
            return adminRepo.save(new Admin(adminDto.getUserName(), adminDto.getEmail(), encodedString));
        } else {
            return "Already Have Admin";
        }
    }

    public ResponseEntity<Map<String, String>> loginAdmin(AdminDto adminDto) {
        HashMap<String, String> response = new HashMap<>();

        String originalInput = adminDto.getPassword();

        if (adminRepo.findUserByEmail(adminDto.getEmail()) == null) {
            response.put("massage", "wrong Email or Condition Wrong");
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        } else {
            Admin userByEmail = adminRepo.findUserByEmail(adminDto.getEmail());
            byte[] decode = Base64.getDecoder().decode(userByEmail.getPassword());
            String decodedPass = new String(decode);


            if (decodedPass.equals(originalInput)) {
                String token = this.jwtTokenGenerator.generateJwtToken(userByEmail);
                response.put("token", token);
                return new ResponseEntity<>(response, HttpStatus.OK);

            } else {
                response.put("massage", "wrong Credentials");
                return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
            }
        }
    }

    public List<Admin> getAllAdmin() {
        return adminRepo.findAll();
    }

    public Object updateAdmin(Integer id, AdminDto adminDto) {
        if (adminRepo.existsById(id)) {
            return adminRepo.save(new Admin(id, adminDto.getUserName(), adminDto.getEmail(), adminDto.getPassword()));
        }
        return "Invalid ID";
    }

    public String deleteAdmin(Integer id) {

            if (adminRepo.existsById(id)) {
                adminRepo.deleteById(id);
                return "Admin Deleted";
            }
            return "No Customer Found";
    }


}
