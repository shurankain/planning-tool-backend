package com.shurankain.planning.app.dto;

import java.util.List;

import lombok.Data;

@Data
public class NoteDto {
    private String noteText;
    private List<String> tasks;
}
