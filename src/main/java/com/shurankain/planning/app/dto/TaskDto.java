package com.shurankain.planning.app.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@Getter
@Builder
public class TaskDto {
    private String id;
    private String taskInfo;
    private LocalDateTime creationDate;
    private Boolean completionStatus;
}
