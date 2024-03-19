package lk.project.riyapola.repo;

import lk.project.riyapola.entity.Admin;
import lk.project.riyapola.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    Customer findUserByEmail(String email);
}
