package SantiagoVillanueva.orderScheduler.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

    private String email;
    private String contrasena;
    private String nombre;
    private Integer dni;
    private String rol;

}
