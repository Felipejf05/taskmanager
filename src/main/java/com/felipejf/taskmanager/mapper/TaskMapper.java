package com.felipejf.taskmanager.mapper;

import com.felipejf.taskmanager.dtos.request.TaskRequestDTO;
import com.felipejf.taskmanager.dtos.response.TaskResponseDTO;
import com.felipejf.taskmanager.entity.Task;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TaskMapper {

    public Task toTask(TaskRequestDTO taskRequestDTO){
        if(taskRequestDTO == null){
            return null;
        }

        Task task = new Task();
        task.setTitle(taskRequestDTO.getTitle());
        task.setDescription(taskRequestDTO.getDescription());
        task.setStatus(taskRequestDTO.getStatus());

        return task;
    }

    public TaskResponseDTO toResponseDTO(Task task){
            if(task == null){
                return null;
            }

            TaskResponseDTO dto = new TaskResponseDTO();
            dto.setId(task.getId());
            dto.setTitle(task.getTitle());
            dto.setDescription(task.getDescription());
            dto.setStatus(task.getStatus());
            dto.setCreatedAt(LocalDate.now());

            return dto;
    }
}
