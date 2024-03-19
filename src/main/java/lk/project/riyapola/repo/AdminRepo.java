package lk.project.riyapola.repo;
import lk.project.riyapola.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin,Integer> {

    Admin findUserByEmail(String email);

//       Admin findUserByEmailAndPassword(String email, String password);
//    Admin findAdminByUserName(String userName);
//    Admin findUserByPassword(String password);
}



