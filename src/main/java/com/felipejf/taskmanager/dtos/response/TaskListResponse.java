package com.felipejf.taskmanager.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TaskListResponse {
    private List<TaskResponseDTO> data;
}
