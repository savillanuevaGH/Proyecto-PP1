package SantiagoVillanueva.orderScheduler.repositories;

import SantiagoVillanueva.orderScheduler.models.UserModel;
import SantiagoVillanueva.orderScheduler.models.dto.UserDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends IBaseRepository<UserModel, Long> {

    //@Query(value = "SELECT u FROM usuario u WHERE u.email LIKE %:email%")
    Optional<UserModel> findByEmail(String email);

    boolean existsByEmail(String email);
    //@Query(value = "SELECT u FROM usuario u WHERE u.id = %:filter%")
    Optional<UserModel> findById(Long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM UserModel u WHERE u.email = %:email%")
    void deleteUser(@Param("email")String userEmail);

}
