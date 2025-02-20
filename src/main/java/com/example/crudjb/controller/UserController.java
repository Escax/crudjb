package com.example.crudjb.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.example.crudjb.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.crudjb.service.UserService;

import java.util.List;
import java.util.UUID;

@Tag(name = "Usuarios", description = "Endpoints de los usuarios")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
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
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.ok(userService.createUser(user));
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
