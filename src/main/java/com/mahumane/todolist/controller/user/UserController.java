package com.mahumane.todolist.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.mahumane.todolist.dto.UsersDto;
import com.mahumane.todolist.service.UsersService;



@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UsersService service;

    @GetMapping("/user/{id}")
    public ResponseEntity<?> selectUser(@PathVariable Long id){
        return service.selectUser(id);
    }

    
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UsersDto dto){
        return service.signup(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsersDto dto){
    return service.login(dto);
    }

    @PutMapping("/update/user/{id}")
    public ResponseEntity<?> updateUser( @PathVariable Long id,@RequestBody UsersDto dto){
    
        return service.updateUser(id,dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        return service.deleteUser(id);
    }
}
