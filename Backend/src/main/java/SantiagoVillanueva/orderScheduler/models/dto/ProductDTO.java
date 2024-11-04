package SantiagoVillanueva.orderScheduler.models.dto;

import SantiagoVillanueva.orderScheduler.models.CategoryModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {

    private Long id;
    private String comida;
    private int stock;
    private String descripcion;
    private String categoria;
}
