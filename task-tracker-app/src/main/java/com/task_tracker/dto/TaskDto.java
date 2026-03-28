package com.task_tracker.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.task_tracker.entity.TaskPriority;
import com.task_tracker.entity.TaskStatus;

public record TaskDto(
		UUID id,
		String title,
		String description,
		LocalDateTime dueDate,
		TaskPriority priority,
		TaskStatus status
		) {

}
