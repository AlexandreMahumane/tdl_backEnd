package com.mahumane.todolist.service;


import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mahumane.todolist.dto.TasksDto;
import com.mahumane.todolist.model.TaskModel;
import com.mahumane.todolist.repository.TaskRepository;
import com.mahumane.todolist.repository.UsersRepository;


import jakarta.transaction.Transactional;




@Service
public class TasksService {

    @Autowired
    private TaskRepository action;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private HistoricService historicService;

    public ResponseEntity<Page<TaskModel>> selectTasks(String username, int page, int size) {
        var user = usersRepository.findByUsername(username);
        var getUserId = user.get().getUser_id();
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(action.findTasksByUserId(getUserId,pageable));
    }

    @Transactional
    public ResponseEntity<?> insertTask(String username,TasksDto dto) {
       var user = usersRepository.findByUsername(username);
       TaskModel taskModel = new TaskModel();
       taskModel.setContent_task(dto.content_task());
       taskModel.setEnd_dateTime(dto.end_dateTime());
       taskModel.setUser_id(user.get().getUser_id());
       taskModel.setCheckTask(false);
       return new ResponseEntity<>(action.save(taskModel),HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<?> updateTask(Long id, TasksDto dto) {
                                
        Optional<TaskModel> task = action.findById(id);
        

        if (task.isEmpty()) {
           throw new NoSuchElementException();
        }

        TaskModel taskModel = task.get();

        taskModel.setContent_task(dto.content_task());

        if (dto.end_dateTime() != null) {
           taskModel.setEnd_dateTime(dto.end_dateTime()); 
        }

        if(dto.checkTask() != null){
            taskModel.setCheckTask(dto.checkTask());
        }
        
        return ResponseEntity.ok(action.save(taskModel));

    }

    @Transactional
    public ResponseEntity<?> deleteTask(Long id) {
        var task = action.findById(id);

        if (task.isEmpty()) {
            throw new NoSuchElementException();
        }
        TaskModel taskModel = task.get();

        historicService.insertTask(taskModel);
        action.delete(taskModel);

        return ResponseEntity.ok().build();
    }

}
