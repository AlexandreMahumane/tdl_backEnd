package com.mahumane.todolist.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.mahumane.todolist.model.TaskModel;

@Repository
public interface TaskRepository  extends JpaRepository<TaskModel,Long>{

    @Query(value="select * from tasks where user_id = :id", nativeQuery = true)
    Page<TaskModel> findTasksByUserId(@Param("id") Long id, Pageable pageable);

    Optional<TaskModel> findById(Long task_id);


}
