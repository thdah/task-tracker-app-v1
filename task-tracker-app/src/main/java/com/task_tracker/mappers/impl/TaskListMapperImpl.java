package com.task_tracker.mappers.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.task_tracker.dto.TaskListDto;
import com.task_tracker.entity.Task;
import com.task_tracker.entity.TaskList;
import com.task_tracker.entity.TaskStatus;
import com.task_tracker.mappers.TaskListMapper;
import com.task_tracker.mappers.TaskMapper;

@Component
public class TaskListMapperImpl implements TaskListMapper {
	
	@Autowired
	private TaskMapper taskMapper;
	
	@Override
	public TaskList fromDto(TaskListDto taskListDto) {
		return new TaskList(
				taskListDto.id(),
				taskListDto.title(),
				taskListDto.description(),
				Optional.ofNullable(taskListDto.tasks())
					.map(tasks -> tasks.stream() // Stream<TaskDto>
							.map(taskMapper::fromDto) //Stream<Task>
							.toList()
					 ) 
					.orElse(null),
				null,
				null
		);
	}

	@Override
	public TaskListDto toDto(TaskList taskList) {
		return new TaskListDto(
				taskList.getId(),
				taskList.getTitle(),
				taskList.getDescription(),
				Optional.ofNullable(taskList.getTasks())
					.map(List::size).orElse(0),
				calculateTaskListProgress(taskList.getTasks()),
				Optional.ofNullable(taskList.getTasks()).map(
						tasks -> tasks.stream().map(taskMapper::toDto).toList()
						).orElse(null)
		);
	}
	
	private Double calculateTaskListProgress(List<Task> tasks) {
		if(null == tasks) {
			return null;
		}
		
		long closedTaskCount = tasks.stream().filter(
				task -> task.getStatus() == TaskStatus.CLOSED
				).count();
		return (double) closedTaskCount / tasks.size();
	}

}
