package SantiagoVillanueva.orderScheduler.services;

import SantiagoVillanueva.orderScheduler.models.BaseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public interface IBaseService <E extends BaseModel, ID extends Serializable> {

    public List<E> findAll() throws Exception;
    public Page<E> findAll(Pageable pageable) throws Exception;
    public Optional<E> findById(ID id) throws Exception;
    public E save(E entity) throws Exception;
    public E update(ID id, E entity) throws Exception;
    boolean delete(ID id) throws Exception;
}
