package com.felipejf.taskmanager.service;

import com.felipejf.taskmanager.entity.Task;
import com.felipejf.taskmanager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService {

    private final TaskRepository taskRepository;

    public Task addTask(Task task){
        log.info("Iniciando a criação da tarefa: {}", task);
        return taskRepository.save(task);
    }

    public List<Task> getTasks(){
        log.info("Listando as tarefas");
        return taskRepository.findAll();
    }

    public Optional<Task> findById(Long id){
        log.info("Encontrando tarefa pelo id: {}", id);
        return taskRepository.findById(id);
    }

    public void deleteTask(Long id){
        log.info("Deletando tarefa com o id: {}", id);
        taskRepository.deleteById(id);
    }

    public Task updateTask(Long id, Task updateTask){
        log.info("Atualizando a tarefa com o id: {}", id);

        return taskRepository.findById(id)
                .map(existingTask -> {
                    existingTask.setTitle(updateTask.getTitle());
                    existingTask.setDescription(updateTask.getDescription());
                    existingTask.setStatus(updateTask.getStatus());
                    existingTask.setUpdatedAt(LocalDateTime.now());

                    Task saved = taskRepository.save(existingTask);
                    log.info("Tarefa atualizada: {}", saved);

                    return saved;
                })
                .orElseThrow(() -> new IllegalArgumentException("Tarefa com o ID " + id + "não encontrada."));
    }
}
