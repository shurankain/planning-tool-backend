package com.shurankain.planning.app.persistence.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class Task {
    private String taskInfo;
    private LocalDateTime creationDate;
    private boolean completionStatus;
}
