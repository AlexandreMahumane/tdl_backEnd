package com.mahumane.todolist.controller.task;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mahumane.todolist.dto.TasksDto;
import com.mahumane.todolist.model.TaskModel;
import com.mahumane.todolist.service.TasksService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("task")
public class TasksController {

    @Autowired
    private TasksService service;

    @GetMapping
    public ResponseEntity<Page<TaskModel>> selectTasks(
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "5") int size,
                                                    JwtAuthenticationToken token){
        var username = token.getName();
        return service.selectTasks(username, page, size);

    }

    @PostMapping
    public ResponseEntity<?> insertTask( @RequestBody @Valid TasksDto dto, JwtAuthenticationToken token){
        var username = token.getName();
        return service.insertTask(username,dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody TasksDto dto){
            return service.updateTask(id,dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        return service.deleteTask(id);
    }


}
