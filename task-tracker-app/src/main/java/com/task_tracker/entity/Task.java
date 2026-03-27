package com.task_tracker.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "tbl_tasks")
@Data
@AllArgsConstructor
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(updatable = false, nullable = false)
	private UUID id;
	
	@Column(updatable = true, nullable = false)
	private String title;
	
	private String description;
	
	@Column(name = "due_date")
	private LocalDateTime dueDate;
	
	@Column(nullable = false)
	private TaskStatus status;
	
	@Column(nullable = false)
	private TaskPriority priority;
	
	@Column(nullable = false)
	private LocalDateTime created;
	
	@Column(nullable = false)
	private LocalDateTime updated;
}
