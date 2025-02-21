package com.example.crudjb.controller;

import com.example.crudjb.repository.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.example.crudjb.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.crudjb.service.UserService;

import java.util.List;
import java.util.UUID;

@Tag(name = "Usuarios", description = "Endpoints de los usuarios")
@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final UserRepository userRepository;


    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        System.out.println("🚀 UserController inicializado correctamente");

    }

    @Operation(summary = "Obtener todos los usuarios", description = "Retorna una lista completa de los usuarios")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Operation(summary = "Obtener usuario por el ID", description = "Retorna el usuario por el ID consultado")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id){
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Guarda nuevos usuarios", description = "Crea nuevos usuarios en la BD")
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user){
        log.info("Usuario recibido: {}", user);

        if (user.getName() == null || user.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre es obligatorio.");
        }
        if (user.getLastName() == null || user.getLastName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El apellido es obligatorio.");
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El email es obligatorio.");
        }
        if (!user.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            return ResponseEntity.badRequest().body("El email no tiene un formato válido.");
        }
        if (user.getHashPassword() == null || user.getHashPassword().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("La contraseña es obligatoria.");
        }
        if (user.getPhoneNumber() == null || user.getPhoneNumber().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El número de teléfono es obligatorio.");
        }
        if (user.getDocumentType() == null || user.getDocumentType().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El tipo de documento es obligatorio.");
        }
        if (user.getDocumentNumber() == null || user.getDocumentNumber().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El número de documento es obligatorio.");
        }

        if (userRepository.findByDocumentNumber(user.getDocumentNumber()).isPresent()) {
            return ResponseEntity.badRequest().body("El número de documento ya está registrado.");
        }

        User savedUser = userService.createUser(user);
        return ResponseEntity.ok(savedUser);
    }


    @Operation(summary = "Actualiza los usuarios", description = "Actualiza los usuarios con el ID")
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody User updateUser){
        return ResponseEntity.ok(userService.updateUser(id, updateUser));
    }

    @Operation(summary = "Elimina los usuarios", description = "Elimina los usuarios con el ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Consulta usuarios por el Email")
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email){
        return userService.getUserByEmail(email)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Consulta los usuarios por el numero de documento")
    @GetMapping("/document/{documentNumber}")
    public ResponseEntity<User> getUserByDocumentNumber(@PathVariable String documentNumber){
        return userService.getUserByDocumentNumber(documentNumber)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }



}
