package com.example.recapProject1October.repository;

import com.example.recapProject1October.dto.KanbanTask;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends MongoRepository<KanbanTask, String> {}
