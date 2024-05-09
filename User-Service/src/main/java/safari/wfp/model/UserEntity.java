package safari.wfp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private List<String> roleNames;
}