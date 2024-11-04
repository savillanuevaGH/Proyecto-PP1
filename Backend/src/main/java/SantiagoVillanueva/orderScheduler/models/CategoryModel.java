package SantiagoVillanueva.orderScheduler.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "categoria")
@Data
public class CategoryModel extends BaseModel{

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", nullable = false)
    private Category categoria;

    public enum Category {
        Vegetariano,
        Carne,
        Postres,
        Bebidas,
        Ensaladas,
        Complementos,
        Sopas,
        Pastas,
        Mariscos,
        Comida_Rápida,   // Usa guiones bajos en lugar de espacios
        Snacks
    }
}
