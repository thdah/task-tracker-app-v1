package com.task_tracker.mappers;

import com.task_tracker.dto.TaskListDto;
import com.task_tracker.entity.TaskList;

public interface TaskListMapper {

	TaskList fromDto(TaskListDto taskListDto);
	
	TaskListDto toDto(TaskList taskList);
}
