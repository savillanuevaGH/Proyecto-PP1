package SantiagoVillanueva.orderScheduler.services;

import SantiagoVillanueva.orderScheduler.models.UserModel;
import SantiagoVillanueva.orderScheduler.models.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface IUserService extends IBaseService<UserModel, Long> {

    Page<UserModel> findAll(Pageable pageable) throws Exception;

    Optional<UserModel> findByEmail(String email) throws Exception;

    @Override
    Optional<UserModel> findById(Long id) throws Exception;

    void deleteUser(String email) throws Exception;

    void register(UserDTO userDTO) throws Exception;

    UserModel updateUser(String email, UserDTO userDTO) throws Exception;

}
