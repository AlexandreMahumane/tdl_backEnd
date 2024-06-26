package com.mahumane.todolist.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.mahumane.todolist.dto.UsersDto;
import com.mahumane.todolist.model.UsersModel;
import com.mahumane.todolist.repository.UsersRepository;

import jakarta.transaction.Transactional;

@Service
public class UsersService {

    @Autowired
    private UsersRepository action;

    @Autowired
    private TokenJwtService jwtService;

    @Autowired
    private PasswordEncoder encoder;

    public ResponseEntity<?> login(UsersDto request) {

        Optional<UsersModel> user = action.findByUsername(request.username());

        if(user.isEmpty()){
            throw new BadCredentialsException("");
        }
        
        UsersModel userModel = user.get();
        if(!userModel.loginIsValid(request, encoder)){
            throw new BadCredentialsException("");
        }
        
        return ResponseEntity.ok(jwtService.generateToken(request));

    }

    @Transactional
    public ResponseEntity<?> signup(UsersDto dto) {
        Optional<UsersModel> user = action.findByUsername(dto.username());
        if (user.isPresent()) {
            throw new DataIntegrityViolationException("");
        }
        UsersModel usersModel = new UsersModel();
        usersModel.setPassword(encoder.encode(dto.password()));
        usersModel.setUsername(dto.username());
        return new ResponseEntity<>(action.save(usersModel),HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<?> updateUser(Long id, UsersDto dto) {
        Optional<UsersModel> user = action.findById(id);
        UsersModel usersModel = user.get();

        if (dto.username() != null) {
             usersModel.setUsername(dto.username());
        }
        
        if (dto.password() != null) {
            usersModel.setPassword(encoder.encode(dto.password()));
        }
        
        return ResponseEntity.ok(action.save(usersModel));
    }

    public ResponseEntity<?> selectUser(Long id) {
        Optional<UsersModel> user = action.findById(id);

        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }

    @Transactional
    public ResponseEntity<?> deleteUser(Long id) {
        Optional<UsersModel> user = action.findById(id);
        UsersModel usersModel = user.get();
        action.delete(usersModel);
        return ResponseEntity.ok().build();
    }


}
