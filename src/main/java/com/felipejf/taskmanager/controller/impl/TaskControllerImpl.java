package com.felipejf.taskmanager.controller.impl;

import com.felipejf.taskmanager.controller.TaskController;
import com.felipejf.taskmanager.dtos.request.TaskRequestDTO;
import com.felipejf.taskmanager.dtos.response.TaskListResponse;
import com.felipejf.taskmanager.dtos.response.TaskResponseDTO;
import com.felipejf.taskmanager.entity.Task;
import com.felipejf.taskmanager.exceptions.ResourceNotFoundException;
import com.felipejf.taskmanager.mapper.TaskMapper;
import com.felipejf.taskmanager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class TaskControllerImpl implements TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @Override
    public ResponseEntity<TaskResponseDTO> createTask(TaskRequestDTO taskRequestDTO) {

        Task saved = taskService.addTask(taskMapper.toTask(taskRequestDTO));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskMapper.toResponseDTO(saved));
    }

    @Override
    public ResponseEntity<TaskListResponse> getTasks() {
        List<TaskResponseDTO> tasks = taskService.getTasks()
                .stream()
                .map(taskMapper::toResponseDTO)
                .toList();
        return ResponseEntity.ok(new TaskListResponse(tasks));
    }

    @Override
    public ResponseEntity<TaskResponseDTO> getTaskId(Long id) {
        Task task = taskService.findById(id);
        return ResponseEntity.ok(taskMapper.toResponseDTO(task));
    }

    @Override
    public ResponseEntity<Void> deleteById(Long id) {
        return null;
    }
}
