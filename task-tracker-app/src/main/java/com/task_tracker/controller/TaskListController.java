package com.task_tracker.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task_tracker.dto.TaskListDto;
import com.task_tracker.entity.TaskList;
import com.task_tracker.mappers.TaskListMapper;
import com.task_tracker.services.TaskListService;

@RestController
@RequestMapping("/task-lists")
public class TaskListController {
	
	@Autowired
	private TaskListService taskListService;
	
	@Autowired
	private TaskListMapper taskListMapper;

	@GetMapping
	public List<TaskListDto> listTaskList() {
		return taskListService.listTaskList()
				.stream()
				.map(taskListMapper::toDto)
				.toList();
	}
	
	@GetMapping("/{task_list_id}")
	public Optional<TaskListDto> getTaskList(@PathVariable("task_list_id") UUID taskListId) {
		return taskListService.getTaskList(taskListId).map(taskListMapper::toDto);
	}
	
	@PostMapping
	public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto) {
		TaskList createdTaskList = taskListService.createTaskList(taskListMapper.fromDto(taskListDto));
		return taskListMapper.toDto(createdTaskList);
	}
	
	@PutMapping("/{task_list_id}")
	public TaskListDto updateTaskList(
			@PathVariable("task_list_id") UUID taskListId, 
			@RequestBody TaskListDto taskListDto
			) {
		TaskList updatedTaskList = taskListService.updateTaskList(taskListId, taskListMapper.fromDto(taskListDto));
		return taskListMapper.toDto(updatedTaskList);
	}
	
	@DeleteMapping("/{task_list_id}")
	public void deleteTaskList(@PathVariable("task_list_id") UUID taskListId) {
		taskListService.deleteTaskList(taskListId);
	}
}
