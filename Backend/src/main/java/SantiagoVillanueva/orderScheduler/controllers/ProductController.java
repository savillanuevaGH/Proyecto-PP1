package SantiagoVillanueva.orderScheduler.controllers;

import SantiagoVillanueva.orderScheduler.models.ProductModel;
import SantiagoVillanueva.orderScheduler.models.dto.CategoryDTO;
import SantiagoVillanueva.orderScheduler.models.dto.ProductDTO;
import SantiagoVillanueva.orderScheduler.services.impl.IProductServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1/productos")
public class ProductController extends BaseControllerImpl<ProductModel, IProductServiceImpl> {

    @GetMapping
    public ResponseEntity<?> findAll() {
        try {
            List<ProductModel> productModels = service.findAll();

            // Convertir la lista de ProductModel a ProductDTO
            List<ProductDTO> productDTOs = productModels.stream()
                    .map(this::convertToDTO) // Convierte cada ProductModel a ProductDTO
                    .collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.OK).body(productDTOs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Error\": \"" + e.getMessage() + "\"}");
        }
    }

    // Método para convertir ProductModel a ProductDTO
    private ProductDTO convertToDTO(ProductModel productModel) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(productModel.getId());
        productDTO.setComida(productModel.getComida());
        productDTO.setStock(productModel.getStock());
        productDTO.setDescripcion(productModel.getDescripcion());

        // Si la categoría no es nula, agregar el DTO de categoría
        if (productModel.getCategoria() != null) {
            CategoryDTO categoryDTO = new CategoryDTO();
            productDTO.setCategoria(productModel.getCategoria().getCategoria().name());
        }

        return productDTO;
    }


    @GetMapping("/search")
    public ResponseEntity<?> findAll(Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findAll(pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"Error\": \"" + e.getMessage() + "\"}"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") Long id) {
        try {
            ProductModel productModel = service.findById(id).get();
            return ResponseEntity.status(HttpStatus.OK).body(productModel);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"Error\": \"" + e.getMessage() + "\"}"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(name = "id") Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/name/{comida}")
    public ResponseEntity<?> findByComida(@PathVariable(name = "comida") String comida) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findByComida(comida));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"Error\": \"" + e.getMessage() + "\"}"));
        }
    }

    @GetMapping("/category/{categoria}")
    public ResponseEntity<?> findByCategoria(@PathVariable(name = "categoria") String categoria) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findByCategoria_Categoria(categoria));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"Error\": \"" + e.getMessage() + "\"}"));
        }
    }

    @PostMapping("/admin")
    public ResponseEntity<?> createProduct(@RequestBody ProductModel productModel) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(productModel));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(("{\"Error\": \"" + e.getMessage() + "\"}"));
        }
    }
}
