package lk.project.riyapola.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private String password;
    private String nic;
    private String email;
    private Integer telephoneNo;
    private String address;
    private String date;
    private String time;

}


