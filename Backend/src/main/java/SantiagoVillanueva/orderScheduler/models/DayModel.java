package SantiagoVillanueva.orderScheduler.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "dia")
@Data
public class DayModel {

    @Id
    @Enumerated(EnumType.STRING)
    private DiaSemana id;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private ProductModel producto;

    // Enum for days
    public enum DiaSemana {
        Lunes, Martes, Miercoles, Jueves, Viernes
    }
}
