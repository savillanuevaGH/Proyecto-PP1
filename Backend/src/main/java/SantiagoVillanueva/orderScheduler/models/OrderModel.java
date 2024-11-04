package SantiagoVillanueva.orderScheduler.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "pedido")
public class OrderModel extends BaseModel{

    @Column(nullable = false)
    private int semana;

    private java.sql.Date fecha_baja;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UserModel usuario;

    @OneToMany(mappedBy = "pedido")
    private List<OrderProduct> pedido_producto;
}
