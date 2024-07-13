package com.mahumane.todolist.dto;

import jakarta.validation.constraints.NotBlank;

<<<<<<< HEAD
public record TasksDto(Long id, @NotBlank String content_task, @NotBlank String end_dateTime, Boolean checkTask) {
=======
public record TasksDto(Long id, @NotBlank String content_task, Boolean checkTask) {
>>>>>>> master

}
