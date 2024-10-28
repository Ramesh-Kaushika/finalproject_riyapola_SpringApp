package lk.project.riyapola.controller;
import lk.project.riyapola.dto.CustomerDto;
import lk.project.riyapola.entity.Customer;
import lk.project.riyapola.service.CustomerService;
import lk.project.riyapola.util.JwtTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
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

    @PostMapping("/register")
    public ResponseEntity<Object> registerCustomer(@RequestBody CustomerDto customerDto) {
        Object ad = customerService.registerCustomer(customerDto);
        return new ResponseEntity<>(ad, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginCustomer(@RequestBody CustomerDto customerDto) {
        return customerService.loginCustomer(customerDto);
    }

    @GetMapping("/all_customers")
    public ResponseEntity<Object> getAllCustomers(@RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            List<Customer> allCustomer = customerService.getAllCustomer();
            return new ResponseEntity<>(allCustomer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("invalid Token", HttpStatus.FORBIDDEN);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCustomer(@RequestHeader(name = "Authorization") String authorizationHeader, @PathVariable Integer id, @RequestBody CustomerDto customerDto) {
        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            Object customer = customerService.updateCustomer(id, customerDto);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("invalid Token", HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@RequestHeader(name = "Authorization") String authorizationHeader, @PathVariable Integer id) {
        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            String deleted = customerService.deleteCustomer(id);
            return new ResponseEntity<>(deleted, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>("invalid Token", HttpStatus.FORBIDDEN);
        }
    }


}
