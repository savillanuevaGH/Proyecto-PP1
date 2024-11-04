package SantiagoVillanueva.orderScheduler.services;

import SantiagoVillanueva.orderScheduler.models.ProductModel;
import SantiagoVillanueva.orderScheduler.models.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IProductService extends IBaseService<ProductModel, Long> {

    Page<ProductModel> findAll(Pageable pageable) throws Exception;

    Optional<ProductModel> findById(Long id) throws Exception;

    Optional<ProductModel> findByComida(String comida) throws Exception;

    List<ProductModel> findByCategoria_Categoria(String categoriaNombre) throws Exception;
}