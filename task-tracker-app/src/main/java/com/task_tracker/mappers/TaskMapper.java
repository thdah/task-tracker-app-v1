package com.task_tracker.mappers;

import com.task_tracker.dto.TaskDto;
import com.task_tracker.entity.Task;

public interface TaskMapper {

	Task fromDto(TaskDto taskDto);
	
	TaskDto toDto(Task task);
}
