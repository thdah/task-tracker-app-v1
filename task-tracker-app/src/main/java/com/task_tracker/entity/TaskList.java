package com.task_tracker.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_task_lists")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskList {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(updatable = false, nullable = false)
	private UUID id;
	
	@Column(nullable = false)
	private String title;
	
	private String description;
	
	@OneToMany(mappedBy = "taskList", cascade = {
			CascadeType.REMOVE, CascadeType.PERSIST
	})
	private List<Task> tasks;
	
	@Column(nullable = false)
	private LocalDateTime created;
	
	@Column(nullable = false)
	private LocalDateTime updated;
}
