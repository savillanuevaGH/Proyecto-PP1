package SantiagoVillanueva.orderScheduler.repositories;

import SantiagoVillanueva.orderScheduler.models.MenuModel;
import SantiagoVillanueva.orderScheduler.models.UserModel;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IMenuRepository extends IBaseRepository<MenuModel, Long> {

    Page<MenuModel> findAll(Pageable pageable);

    @Override
    Optional<MenuModel> findById(Long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM MenuModel m WHERE m.id = %:id%")
    void deleteMenu(@Param("id") Long menuID);

    Optional<MenuModel> findByUsuario(UserModel userModel);
}
