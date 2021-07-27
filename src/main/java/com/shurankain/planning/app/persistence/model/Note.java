package com.shurankain.planning.app.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "notes")
public class Note {
    @Id
    private String id;
    private String noteText;
    private LocalDateTime creationDate;
    private List<Task> tasks;
}
