package com.mahumane.todolist.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mahumane.todolist.model.HistoricModel;

@Repository
public interface HistoricRepository extends JpaRepository<HistoricModel,Long>{

    @Query(value = "select * from historic where user_id = :user_id", nativeQuery = true)
    Page<HistoricModel> findByUserId(@Param("user_id") long user_id, Pageable pageable);

}
