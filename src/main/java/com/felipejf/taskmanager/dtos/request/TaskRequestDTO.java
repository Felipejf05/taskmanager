package com.felipejf.taskmanager.dtos.request;

import com.felipejf.taskmanager.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TaskRequestDTO {

    private String title;
    private String description;
    private TaskStatus status;
}
