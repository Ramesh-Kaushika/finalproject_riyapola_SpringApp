package lk.project.riyapola.repo;
import lk.project.riyapola.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collections;

public interface AdminRepo extends JpaRepository<Admin,Integer> {

    Admin findUserByEmailAndPassword(String email, String password);



}
