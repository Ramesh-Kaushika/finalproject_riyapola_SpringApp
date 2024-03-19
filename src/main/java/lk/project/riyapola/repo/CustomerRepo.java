package lk.project.riyapola.repo;

import lk.project.riyapola.entity.Admin;
import lk.project.riyapola.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    Customer findUserByEmail(String email);
}
