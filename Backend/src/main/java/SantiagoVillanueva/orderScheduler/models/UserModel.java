package SantiagoVillanueva.orderScheduler.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "usuario")
@Data
public class UserModel extends BaseModel{

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String contrasena;

    @Column(nullable = false, length = 8)
    private Integer dni;

    @Column(nullable = false, length = 100)
    private String nombre;

    private java.sql.Date fecha_baja;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;

    @OneToMany(mappedBy = "usuario")
    private List<OrderModel> pedidos;

    @OneToMany(mappedBy = "usuario")
    private List<MenuModel> menus;

    // Enum for roles
    public enum Rol {
        Empleado, AdminRestaurante
    }
}
