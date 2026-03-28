package com.task_tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping
	public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto) {
		TaskList createdTaskList = taskListService.createTaskList(taskListMapper.fromDto(taskListDto));
		return taskListMapper.toDto(createdTaskList);
	}
}
