package com.mahumane.todolist.dto;

import jakarta.validation.constraints.NotBlank;

public record TasksDto(Long id, @NotBlank String content_task, Boolean checkTask) {

}
