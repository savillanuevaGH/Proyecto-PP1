package SantiagoVillanueva.orderScheduler.services.impl;

import SantiagoVillanueva.orderScheduler.models.MenuModel;
import SantiagoVillanueva.orderScheduler.models.UserModel;
import SantiagoVillanueva.orderScheduler.repositories.IMenuRepository;
import SantiagoVillanueva.orderScheduler.services.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IMenuServiceImpl extends BaseServiceImpl<MenuModel, Long> implements IMenuService {

    @Autowired
    protected IMenuRepository menuRepository;

    @Override
    public Page<MenuModel> findAll(Pageable pageable) throws Exception {
        try {
            return menuRepository.findAll(pageable);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Optional<MenuModel> findById(Long id) throws Exception {
        try {
            return menuRepository.findById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deleteMenu(Long id) throws Exception {
        try {
            if (!menuRepository.findById(id).isPresent()) {
                throw new Exception();
            }
            menuRepository.deleteMenu(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Optional<MenuModel> findByUsuario(UserModel userModel) throws Exception {
        try {
            return menuRepository.findByUsuario(userModel);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
