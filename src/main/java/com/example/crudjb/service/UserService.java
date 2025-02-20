package com.example.crudjb.service;

import com.example.crudjb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.crudjb.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(UUID id){
        return userRepository.findById(id);
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User updateUser(UUID id, User updateUser){
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(updateUser.getName());
                    user.setLastName(updateUser.getLastName());
                    user.setEmail(updateUser.getEmail());
                    user.setPhoneNumber(updateUser.getPhoneNumber());
                    user.setPhoneAuxiliar(updateUser.getPhoneAuxiliar());
                    user.setDocumentType(updateUser.getDocumentType());
                    user.setDocumentNumber(updateUser.getDocumentNumber());
                    user.setActive(updateUser.isActive());
                    return userRepository.save(user);


                }).orElseThrow(()-> new RuntimeException("Usuario no encontrado con el Id: " + id));
    }

    public void deleteUser(UUID id){
        userRepository.findById(id).ifPresent(user -> {
            user.setDeletedAt(java.time.LocalDateTime.now());
            userRepository.save(user);
        });
    }

    public Optional<User> getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Optional<User> getUserByDocumentNumber(String documentNumber){
        return userRepository.findByDocumentNumber(documentNumber);
    }


}
