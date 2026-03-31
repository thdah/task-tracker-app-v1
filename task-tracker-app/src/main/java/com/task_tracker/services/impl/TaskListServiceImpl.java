package com.task_tracker.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

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

	@Override
	public Optional<TaskList> getTaskList(UUID id) {
		return taskListRepository.findById(id);
	}

	@Override
	public TaskList updateTaskList(UUID taskListId, TaskList taskList) {
		
		if(null == taskList.getId()) {
			throw new IllegalArgumentException("Task List must have an ID.");
		}
		
		if(!Objects.equals(taskList.getId(), taskListId)) {
			throw new IllegalArgumentException("Attempting to change Task List Id, this is not permitted.");
		}
		
		TaskList existingTaskList = taskListRepository.findById(taskListId)
				.orElseThrow(() -> new IllegalArgumentException("Task List Not Found."));
		
		existingTaskList.setTitle(taskList.getTitle());
		existingTaskList.setDescription(taskList.getDescription());
		existingTaskList.setUpdated(LocalDateTime.now());
		return taskListRepository.save(existingTaskList);
	}

	@Override
	public void deleteTaskList(UUID taskListId) {
		taskListRepository.deleteById(taskListId);
		
	}

}
