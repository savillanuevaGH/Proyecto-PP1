package SantiagoVillanueva.orderScheduler.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "menu")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class MenuModel extends BaseModel {

    @Column(nullable = false)
    private int semana;

    private java.sql.Date fecha_baja;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UserModel usuario;

    @ManyToMany
    @JoinTable(
            name = "menu_producto",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<ProductModel> productos;
}
