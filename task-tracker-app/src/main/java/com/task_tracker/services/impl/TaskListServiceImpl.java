package com.task_tracker.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task_tracker.entity.TaskList;
import com.task_tracker.repository.TaskListRepository;
import com.task_tracker.services.TaskListService;

@Service
public class TaskListServiceImpl implements TaskListService {
	
	@Autowired
	private TaskListRepository taskListRepository;

	@Override
	public List<TaskList> listTaskList() {
		return taskListRepository.findAll();
	}

	@Override
	public TaskList createTaskList(TaskList taskList) {
		if(null != taskList.getId()) {
			throw new IllegalArgumentException("Task list already has an ID");
		}
		if(null == taskList.getTitle() || taskList.getTitle().isBlank()) {
			throw new IllegalArgumentException("Task list title must be present.");
		}
		
		LocalDateTime now = LocalDateTime.now();
		return taskListRepository.save(new TaskList(
				null,
				taskList.getTitle(),
				taskList.getDescription(),
				null,
				now,
				now
		));
	}

}
