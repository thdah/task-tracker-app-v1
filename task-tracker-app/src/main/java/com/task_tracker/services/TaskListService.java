package com.task_tracker.services;

import java.util.List;

import com.task_tracker.entity.TaskList;

public interface TaskListService {
	List<TaskList> listTaskList();
	TaskList createTaskList(TaskList taskList);
}
