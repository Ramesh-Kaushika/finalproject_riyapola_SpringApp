package lk.project.riyapola.controller;

import lk.project.riyapola.dto.AdminDto;
import lk.project.riyapola.dto.CustomerDto;
import lk.project.riyapola.entity.Admin;
import lk.project.riyapola.service.CustomerService;
import lk.project.riyapola.util.JwtTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final JwtTokenGenerator jwtTokenGenerator;


    @Autowired
    public CustomerController(CustomerService customerService, JwtTokenGenerator jwtTokenGenerator) {
        this.customerService = customerService;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @PostMapping("/register/customer")
    public ResponseEntity<Object> registerCustomer(@RequestBody CustomerDto customerDto) {
        Object ad = customerService.registerCustomer(customerDto);
        return new ResponseEntity<>(ad, HttpStatus.CREATED);
    }

    @PostMapping("/login/customer")
    public ResponseEntity<Map<String, String>> loginCustomer(@RequestBody CustomerDto customerDto) {
        return customerService.loginCustomer(customerDto);
    }

    @GetMapping("/all_customers")
    public ResponseEntity<Object> getAllCustomers(@RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            List<Admin> allAdmin = customerService.getAllCustomer();
            return new ResponseEntity<>(allAdmin, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("invalid Token", HttpStatus.FORBIDDEN);
        }

    }


}
