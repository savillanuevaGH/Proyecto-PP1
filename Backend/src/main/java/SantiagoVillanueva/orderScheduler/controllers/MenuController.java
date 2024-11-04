package SantiagoVillanueva.orderScheduler.controllers;

import SantiagoVillanueva.orderScheduler.models.MenuModel;
import SantiagoVillanueva.orderScheduler.services.impl.IMenuServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1/menus")
public class MenuController extends BaseControllerImpl<MenuModel, IMenuServiceImpl> {

    @GetMapping
    public ResponseEntity<?> findAll() {
        try {
            List<MenuModel> menuModels = service.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(menuModels);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"Error\": \"" + e.getMessage() + "\"}"));
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(Pageable pageable) {
        try {
            Page<MenuModel> menuModels = service.findAll(pageable);
            return ResponseEntity.status(HttpStatus.OK).body(menuModels);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"Error\": \"" + e.getMessage() + "\"}"));
        }
    }
}
