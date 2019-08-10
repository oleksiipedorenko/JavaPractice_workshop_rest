package ua.skillsup.practice.restworkshop.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ua.skillsup.practice.restworkshop.models.SubTask;
import ua.skillsup.practice.restworkshop.models.Task;
import ua.skillsup.practice.restworkshop.services.TaskService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/tasks")
public class TaskController {

	private final Logger logger = LoggerFactory.getLogger(TaskController.class);

	private final TaskService service;

	public TaskController(TaskService service) {
		this.service = service;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Task> getAllTasks(
			@RequestParam(required = false, name = "from")
			@DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate from,
			@RequestParam(required = false, name = "to")
			@DateTimeFormat(pattern = "dd/MM/yyyy")LocalDate to
	) {
		logger.info("Incoming parameters are: from '{}', to '{}'", from, to);
		return service.getAll(from, to);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void submitTask(@RequestBody @Valid Task newTask) {
		logger.info("New task submitter: {}", newTask);
		service.store(newTask);
	}

	@PostMapping(path = "/{taskId}/sub-tasks",
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addSubtask(@RequestBody SubTask subTask,
	                       @PathVariable(name = "taskId") long taskId) {
		logger.info("Task id -> {}, sub task -> {}" , taskId, subTask);
		service.addSubtask(taskId, subTask);
	}
}