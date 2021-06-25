package com.shurankain.planning.app.persistence.repsoitory;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.shurankain.planning.app.persistence.model.Note;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {

    Optional<Note> findNoteByNoteTextContaining(String text);
}
