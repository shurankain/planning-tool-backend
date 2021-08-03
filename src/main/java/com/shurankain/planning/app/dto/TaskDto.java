package com.shurankain.planning.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private String taskInfo;
    private LocalDateTime creationDate;
    private Boolean completionStatus;
}
