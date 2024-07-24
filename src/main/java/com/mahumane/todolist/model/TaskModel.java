package com.mahumane.todolist.model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
public class TaskModel {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long task_id;

    private String content_task;

    private String create_dateTime;

    private String end_dateTime;

    private Boolean checkTask;
    
    private Long user_id;

    public TaskModel(Long task_id, String content_task, String create_dateTime, String end_dateTime, Boolean checkTask,
            Long user_id) {
        this.task_id = task_id;
        this.content_task = content_task;
        this.create_dateTime = create_dateTime;
        this.end_dateTime = end_dateTime;
        this.checkTask = checkTask;
        this.user_id = user_id;
    }
    
    public TaskModel() {
    }
    public Long getTask_id() {
        return task_id;
    }
    public void setTask_id(Long task_id) {
        this.task_id = task_id;
    }
    public String getContent_task() {
        return content_task;
    }
    public void setContent_task(String content_task) {
        this.content_task = content_task;
    }
    public 	String getCreate_dateTime() {
        return create_dateTime;
    }
    public void setCreate_dateTime(String create_dateTime) {
        this.create_dateTime = create_dateTime;
    }
    public String getEnd_dateTime() {
        return end_dateTime;
    }
    public void setEnd_dateTime(String end_dateTime) {
        this.end_dateTime = end_dateTime;
    }
    public Boolean getCheckTask() {
        return checkTask;
    }
    public void setCheckTask(Boolean checkTask) {
        this.checkTask = checkTask;
    }
    public Long getUser_id() {
        return user_id;
    }
    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    
    
}