package com.mahumane.todolist.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mahumane.todolist.model.HistoricModel;
import com.mahumane.todolist.model.TaskModel;
import com.mahumane.todolist.repository.HistoricRepository;
import com.mahumane.todolist.repository.UsersRepository;

@Service
public class HistoricService {
    @Autowired
    private HistoricRepository action;

    @Autowired
    private UsersRepository usersRepository;

    public ResponseEntity<?> insertTask(TaskModel taskModel) {
        HistoricModel historicModel = new HistoricModel();
        historicModel.setContent_task(taskModel.getContent_task());
        historicModel.setCreate_dateTime(taskModel.getCreate_dateTime());
        historicModel.setEnd_dateTime(taskModel.getEnd_dateTime());
        historicModel.setUser_id(taskModel.getUser_id());
        return new ResponseEntity<>(action.save(historicModel), HttpStatus.CREATED);
    }

    public ResponseEntity<Page<HistoricModel>> selectHistoric(String username, int page, int size) {
        var user = usersRepository.findByUsername(username);
        var getUserId = user.get().getUser_id();
        
        Pageable pageable = PageRequest.of(page, size);
        var historicList = action.findByUserId(getUserId, pageable);
        return ResponseEntity.ok(historicList);
    }

    public ResponseEntity<?> deleteHistoric(Long id) {
        Optional<HistoricModel>historic =action.findById(id);
        HistoricModel historicModel = historic.get();

        action.delete(historicModel);
        return ResponseEntity.ok().build();
    }


}
