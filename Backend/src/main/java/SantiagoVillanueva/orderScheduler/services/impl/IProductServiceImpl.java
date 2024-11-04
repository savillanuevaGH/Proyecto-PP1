package SantiagoVillanueva.orderScheduler.services.impl;

import SantiagoVillanueva.orderScheduler.models.CategoryModel;
import SantiagoVillanueva.orderScheduler.models.ProductModel;
import SantiagoVillanueva.orderScheduler.models.dto.ProductDTO;
import SantiagoVillanueva.orderScheduler.repositories.IProductRepository;
import SantiagoVillanueva.orderScheduler.services.IProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IProductServiceImpl extends BaseServiceImpl<ProductModel, Long> implements IProductService {

    @Autowired
    protected IProductRepository productRepository;

    @Override
    @Transactional()
    public List<ProductModel> findAll() throws Exception {
        try {
            List<ProductModel> productModels = productRepository.findAll();
            return productModels;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<ProductModel> findAll(Pageable pageable) throws Exception {
        try {
            return productRepository.findAll(pageable);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Optional<ProductModel> findById(Long id) throws Exception {
        try {
            return productRepository.findById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Optional<ProductModel> findByComida(String comida) throws Exception {
        try {
            return productRepository.findByComida(comida);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<ProductModel> findByCategoria_Categoria(String categoriaNombre) throws Exception {
        try {
            return productRepository.findByCategoria_Categoria(CategoryModel.Category.valueOf(categoriaNombre));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
