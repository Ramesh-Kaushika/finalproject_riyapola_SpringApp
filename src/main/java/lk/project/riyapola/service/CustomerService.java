package lk.project.riyapola.service;

import lk.project.riyapola.dto.AdminDto;
import lk.project.riyapola.dto.CustomerDto;
import lk.project.riyapola.entity.Admin;
import lk.project.riyapola.entity.Customer;
import lk.project.riyapola.repo.CustomerRepo;
import lk.project.riyapola.util.JwtTokenGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerService {
    private final CustomerRepo  customerRepo;
    private final JwtTokenGenerator jwtTokenGenerator;

    public CustomerService(CustomerRepo customerRepo, JwtTokenGenerator jwtTokenGenerator) {
        this.customerRepo = customerRepo;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    public Object registerCustomer(CustomerDto customerDto) {
        if (customerRepo.findUserByEmail(customerDto.getEmail()) == null) {
            String originalInput = customerDto.getPassword();
            String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
            return customerRepo.save(new Customer(customerDto.getFirstName(), customerDto.getLastName(),customerDto.getEmail(),customerDto.getTelephoneNo(), encodedString));
        } else {
            return "Already Have Admin";
        }
    }

    public ResponseEntity<Map<String, String>> loginCustomer(CustomerDto customerDto) {
        HashMap<String, String> response = new HashMap<>();

        String originalInput = customerDto.getPassword();

        if (customerRepo.findUserByEmail(customerDto.getEmail()) == null) {
            response.put("massage", "wrong Email or Condition Wrong");
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        } else {
            Customer userByEmail = customerRepo.findUserByEmail(customerDto.getEmail());
            byte[] decode = Base64.getDecoder().decode(userByEmail.getPassword());
            String decodedPass = new String(decode);


            if (decodedPass.equals(originalInput)) {
                String token = this.jwtTokenGenerator.generateJwtTokenCustomer(userByEmail);
                response.put("token", token);
                return new ResponseEntity<>(response, HttpStatus.OK);

            } else {
                response.put("massage", "wrong Credentials");
                return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
            }
        }
    }
}
