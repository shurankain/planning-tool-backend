package com.shurankain.planning.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoteWithTasksDto {
    private String noteText;
    private List<TaskDto> tasks;
}
