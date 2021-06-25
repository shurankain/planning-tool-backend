package com.shurankain.planning.app.persistence.repsoitory;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.shurankain.planning.app.persistence.model.Task;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {
}
