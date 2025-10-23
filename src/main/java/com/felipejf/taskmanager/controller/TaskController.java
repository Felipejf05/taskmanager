package com.felipejf.taskmanager.controller;

import com.felipejf.taskmanager.dtos.request.TaskRequestDTO;
import com.felipejf.taskmanager.dtos.response.TaskListResponse;
import com.felipejf.taskmanager.dtos.response.TaskResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping("/v1/task")
public interface TaskController {

    @PostMapping
    @Operation(summary = "Cria uma nova tarefa")
    @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso",
            content = @Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = TaskResponseDTO.class)))
    @ApiResponse(responseCode = "400", description = "Erro na validação")
    ResponseEntity<TaskResponseDTO> createTask(@Valid @RequestBody TaskRequestDTO taskRequestDTO);

    @GetMapping("/list")
    @Operation(summary = "Retorna uma lista de tarefas")
    @ApiResponse(responseCode = "200", description = "Lista de tarefas retornada com sucesso",
            content = @Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = TaskListResponse.class)))
    ResponseEntity<TaskListResponse> getTasks();

    @GetMapping("/{id}")
    @Operation(summary = "Retorna uma tarefa pelo ID")
    @ApiResponse(responseCode = "200", description = "Tarefa encontrada com sucesso",
            content = @Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = TaskResponseDTO.class)))
    @ApiResponse(responseCode = "401", description = "Tarefa não encontrada")
    ResponseEntity<TaskResponseDTO> getTaskId(@PathVariable Long id);

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza as informações da tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso",
                content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = TaskResponseDTO.class)))
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long id, @Valid @RequestBody TaskRequestDTO taskRequestDTO);

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete uma tarefa pelo ID")
    @ApiResponse(responseCode = "204", description = "Tarefa deletada com sucesso")
    @ApiResponse(responseCode = "401", description = "Tarefa não encontrada")
    ResponseEntity<Void> deleteById(@PathVariable Long id);
}
