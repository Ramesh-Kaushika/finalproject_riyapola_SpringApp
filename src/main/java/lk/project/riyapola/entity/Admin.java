package lk.project.riyapola.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;



@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String userName;

    private String email;

    private String password;



    public Admin(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }




    public Admin(Integer id, String userName, String email) {
        this.userName = userName;
        this.email = email;
        this.id=id;
    }



}
