package SantiagoVillanueva.orderScheduler.services.impl;

import SantiagoVillanueva.orderScheduler.models.UserModel;
import SantiagoVillanueva.orderScheduler.models.dto.UserDTO;
import SantiagoVillanueva.orderScheduler.repositories.IUserRepository;
import SantiagoVillanueva.orderScheduler.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IUserServiceImpl extends BaseServiceImpl<UserModel, Long> implements IUserService {

    @Autowired
    protected IUserRepository userRepository;

    @Override
    public Page<UserModel> findAll(Pageable pageable) throws Exception {
        try {
            return userRepository.findAll(pageable);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Optional<UserModel> findByEmail(String email) throws Exception{
        try {
            return userRepository.findByEmail(email);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Optional<UserModel> findById(Long id) throws Exception {
        try {
            return userRepository.findById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deleteUser(String email) throws Exception {
        try {
            if (!userRepository.findByEmail(email).isPresent()) {
                throw new Exception();
            }
            userRepository.deleteUser(email);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void register(UserDTO userDto) {

        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new RuntimeException("El email ya está en uso.");
        }

        // Convertir el DTO a la entidad User
        UserModel user = new UserModel();
        user.setEmail(userDto.getEmail());
        user.setContrasena(userDto.getContrasena()); // Asegúrate de encriptar la contraseña
        user.setNombre(userDto.getNombre());
        user.setDni(userDto.getDni());
        user.setRol(UserModel.Rol.valueOf(userDto.getRol()));

        // Guardar el usuario
        userRepository.save(user);
    }

    @Override
    public UserModel updateUser(String email, UserDTO userDTO) throws Exception {
        Optional<UserModel> userModelOptional = userRepository.findByEmail(email);

        if (!userModelOptional.isPresent()) {
            throw new Exception("Usuario no encontrado.");
        }

        UserModel userModel = userModelOptional.get();
        userModel.setNombre(userDTO.getNombre());
        userModel.setDni(userDTO.getDni());
        if (userDTO.getContrasena() != null) {
            userModel.setContrasena(userDTO.getContrasena()); // Asegúrate de manejar el hashing aquí
        }

        return userRepository.save(userModel);
    }
}