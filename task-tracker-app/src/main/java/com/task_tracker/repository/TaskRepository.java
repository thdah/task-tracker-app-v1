package com.task_tracker.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task_tracker.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
	List<Task> findByTaskListId(UUID taskListId);
	Optional<Task> findByTaskListIdAndId(UUID taskListId, UUID id);
}
