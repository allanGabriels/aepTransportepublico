package com.controleonibus.aeptransportepublico.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.controleonibus.aeptransportepublico.dto.UserDto;
import com.controleonibus.aeptransportepublico.entity.User;

import com.controleonibus.aeptransportepublico.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    public UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("{id}")
    public User find(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }

    @PostMapping
    public User save(@RequestBody UserDto userDto) {
        User user = new User(
                userDto.fullName(),
                userDto.cpf(),
                userDto.age(),
                userDto.role());
        return userRepository.save(user);
    }

    @PutMapping("{id}")
    public User update(@PathVariable Long id, @RequestBody UserDto userDto) {
        User existinUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        existinUser.setFullName(userDto.fullName());
        existinUser.setCpf(userDto.cpf());
        existinUser.setAge(userDto.age());
        existinUser.setRole(userDto.role());

        return userRepository.save(existinUser);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        userRepository.delete(user);
        return ResponseEntity.noContent().build();
    }

}
