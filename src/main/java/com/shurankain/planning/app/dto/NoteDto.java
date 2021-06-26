package com.shurankain.planning.app.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NoteDto {
    private String noteText;
    private List<String> tasks;
}
