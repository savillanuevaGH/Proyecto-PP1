package SantiagoVillanueva.orderScheduler.services;

import SantiagoVillanueva.orderScheduler.models.MenuModel;
import SantiagoVillanueva.orderScheduler.models.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface IMenuService extends IBaseService<MenuModel, Long> {

    Page<MenuModel> findAll(Pageable pageable) throws Exception;

    Optional<MenuModel> findById(Long id) throws Exception;

    void deleteMenu(Long id) throws Exception;

    Optional<MenuModel> findByUsuario(UserModel userModel) throws Exception;
}
