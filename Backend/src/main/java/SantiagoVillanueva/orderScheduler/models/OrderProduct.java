package SantiagoVillanueva.orderScheduler.models;

import jakarta.persistence.*;

@Entity
@Table(name = "pedido_producto")
public class OrderProduct extends BaseModel {

    @Column(nullable = false)
    private int dia;

    @Column(nullable = false)
    private int cantidad;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private OrderModel pedido;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private ProductModel producto;
}
