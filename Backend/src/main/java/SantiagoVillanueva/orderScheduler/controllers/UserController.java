package SantiagoVillanueva.orderScheduler.controllers;

import SantiagoVillanueva.orderScheduler.models.UserModel;
import SantiagoVillanueva.orderScheduler.models.dto.UserDTO;
import SantiagoVillanueva.orderScheduler.services.impl.IUserServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1/usuarios")
public class UserController extends BaseControllerImpl<UserModel, IUserServiceImpl> {

    @GetMapping
    public ResponseEntity<?> findAll() {
        try {
            List<UserModel> userModels = service.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(userModels);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"Error\": \"" + e.getMessage() + "\"}"));
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> findAll(Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findAll(pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"Error\": \"" + e.getMessage() + "\"}"));
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") Long id) {
        try {
            UserModel userModel = service.findById(id).get();
            return ResponseEntity.status(HttpStatus.OK).body(userModel);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"Error\": \"" + e.getMessage() + "\"}"));
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable(name = "email") String email) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findByEmail(email));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"Error\": \"" + e.getMessage() + "\"}"));
        }
    }

    @PostMapping("/new")
    public ResponseEntity<?> createUser(@RequestBody UserModel userModel) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(userModel));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(("{\"Error\": \"" + e.getMessage() + "\"}"));
        }
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable String email) {
        try {
            service.deleteUser(email);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDto) {
        try {
            // Lógica para registrar el usuario
            service.register(userDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado con éxito.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Error\": \"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) throws Exception {
        Optional<UserModel> userModelOpt = service.findByEmail(userDTO.getEmail());

        // Verificar si el usuario existe
        if (!userModelOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas.");
        }

        UserModel userModel = userModelOpt.get();

        // Aquí debes usar un método para verificar la contraseña si está hasheada
        if (!userModel.getContrasena().equals(userDTO.getContrasena())) { // Cambia esto si usas hashing
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\": \"Credenciales incorrectas.\"}");
        }

        // Aquí puedes devolver información del usuario o generar un token
        return ResponseEntity.ok("{\"message\": \"Login exitoso.\"}");
    }

    @PutMapping("/{email}")
    public ResponseEntity<?> updateUser(@PathVariable String email, @RequestBody UserDTO userUpdateDTO) {
        try {
            UserModel updatedUser = service.updateUser(email, userUpdateDTO);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Error\": \"" + e.getMessage() + "\"}");
        }
    }

}