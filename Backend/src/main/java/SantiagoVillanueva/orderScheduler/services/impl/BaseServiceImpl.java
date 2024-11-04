package SantiagoVillanueva.orderScheduler.services.impl;

import SantiagoVillanueva.orderScheduler.models.BaseModel;
import SantiagoVillanueva.orderScheduler.repositories.IBaseRepository;
import SantiagoVillanueva.orderScheduler.services.IBaseService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public abstract class BaseServiceImpl <E extends BaseModel, ID extends Serializable> implements IBaseService<E,ID> {

    @Autowired
    protected IBaseRepository<E, ID> baseRepository;

    @Override
    @Transactional
    public List<E> findAll() throws Exception {
        try{
            return (List<E>) baseRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<E> findAll(Pageable pageable) throws Exception {
        try{
            return (Page<E>) baseRepository.findAll(pageable);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Optional<E> findById(ID id) throws Exception {
        return baseRepository.findById(id);
    }

    @Override
    @Transactional
    public E save(E entity) throws Exception {
        try {
            entity = baseRepository.save(entity);
            return entity;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E update(ID id, E entity) throws Exception {
        Optional<E> entityOptional = baseRepository.findById(id);
        if (entityOptional.isPresent()) {
            E entityToUpdate = entityOptional.get();
            return baseRepository.save(entityToUpdate);
        } else {
            throw new EntityNotFoundException("Entity not found for id: " + id);
        }
    }


    @Override
    @Transactional
    public boolean delete(ID id) throws Exception {
        try {
            if (baseRepository.existsById(id)) {
                baseRepository.deleteById(id);
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
