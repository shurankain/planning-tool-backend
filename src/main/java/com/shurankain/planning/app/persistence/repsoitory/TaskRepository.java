package com.shurankain.planning.app.persistence.repsoitory;

import com.shurankain.planning.app.persistence.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {

    Long countAllByCompletionStatusIsFalse();
}
