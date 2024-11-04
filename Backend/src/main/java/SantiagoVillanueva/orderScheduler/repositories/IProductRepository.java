package SantiagoVillanueva.orderScheduler.repositories;

import SantiagoVillanueva.orderScheduler.models.CategoryModel;
import SantiagoVillanueva.orderScheduler.models.ProductModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepository extends IBaseRepository<ProductModel, Long> {

    Optional<ProductModel> findByComida(String comida) throws Exception;

    @Override
    Optional<ProductModel> findById(Long id);

    List<ProductModel> findByCategoria_Categoria(CategoryModel.Category categoria) throws Exception;
}
