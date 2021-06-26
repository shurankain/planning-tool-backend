package com.shurankain.planning.app.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Builder
public class TaskDto {
    private String id;
    private String taskInfo;
    private LocalDateTime creationDate;
    private Boolean completionStatus;
}
