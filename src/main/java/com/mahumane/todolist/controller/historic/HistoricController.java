package com.mahumane.todolist.controller.historic;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mahumane.todolist.model.HistoricModel;
import com.mahumane.todolist.service.HistoricService;



@RestController
@RequestMapping("historic")
public class HistoricController {

    @Autowired
    private HistoricService service;

    @GetMapping
    public ResponseEntity<Page<HistoricModel>> selectHistoric(
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "5") int size,
                                                JwtAuthenticationToken token){
        var username = token.getName();

        return service.selectHistoric(username, page, size);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHistoric(@PathVariable Long id){
        return service.deleteHistoric(id);
    }
}
