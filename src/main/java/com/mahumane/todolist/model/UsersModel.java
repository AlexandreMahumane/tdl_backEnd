package com.mahumane.todolist.model;



import org.springframework.security.crypto.password.PasswordEncoder;

import com.mahumane.todolist.dto.UsersDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UsersModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;

    private String username;

    private String password;


    public UsersModel(long user_id, String username, String password) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
    }

    public UsersModel() {
    }

    public UsersModel(Long id, UsersDto dto) {
        this.user_id = id;
        this.username = dto.username();
        this.password = dto.password();
    }

    public boolean loginIsValid(UsersDto request, PasswordEncoder encoder){
        return encoder.matches(request.password(), this.password);
    }
    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }    
}
