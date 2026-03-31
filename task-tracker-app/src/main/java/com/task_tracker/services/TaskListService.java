package com.task_tracker.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.task_tracker.entity.TaskList;

public interface TaskListService {
	List<TaskList> listTaskList();
	
	TaskList createTaskList(TaskList taskList);
	
	Optional<TaskList> getTaskList(UUID taskListId);
	
	TaskList updateTaskList(UUID taskListId, TaskList taskList);
	
	void deleteTaskList(UUID taskListId);
}
