package SantiagoVillanueva.orderScheduler.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "producto")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ProductModel extends BaseModel{

    @Column(name = "comida", nullable = false, length = 255)
    private String comida;

    @Column(name = "stock", nullable = false)
    private int stock = 0;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToMany(mappedBy = "productos")
    @JsonIgnore
    private List<MenuModel> menus;

    @OneToMany(mappedBy = "producto")
    private List<OrderProduct> pedido_producto;

    @OneToMany(mappedBy = "producto")
    private List<DayModel> dias;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    @JsonBackReference
    private CategoryModel categoria;
}