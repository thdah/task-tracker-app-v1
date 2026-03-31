package com.task_tracker.dto;

public record ErrorResponse(
		int status,
		String message,
		String details
		) {

}
