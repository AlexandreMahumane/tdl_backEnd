package com.mahumane.todolist.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mahumane.todolist.model.UsersModel;


@Repository
public interface UsersRepository extends JpaRepository<UsersModel,Long> {

    Optional<UsersModel> findByUsername(String username);
    Optional<UsersModel> findById(Long id);





}
