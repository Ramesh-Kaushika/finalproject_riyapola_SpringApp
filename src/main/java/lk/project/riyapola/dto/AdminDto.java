package lk.project.riyapola.dto;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminDto {

    private int id;
    private String userName;
    private String email;
    private String password;

}
