package com.shurankain.planning.app.persistence.repsoitory;

import com.shurankain.planning.app.persistence.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {

    List<Note> findAllByNoteTextContaining(String text);
}
