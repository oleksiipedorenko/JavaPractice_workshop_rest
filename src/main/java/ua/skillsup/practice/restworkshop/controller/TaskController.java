package ua.skillsup.practice.restworkshop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ua.skillsup.practice.restworkshop.model.Task;
import ua.skillsup.practice.restworkshop.service.TaskService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("tasks")
public class TaskController {

	private Logger logger = LoggerFactory.getLogger(TaskController.class);

	private final TaskService service;

	@Autowired
	public TaskController(TaskService service) {
		this.service = service;
	}

	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Task> getAllTasks(
			@RequestParam(required = false, name = "from")
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
			@RequestParam(required = false, name = "to")
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
	) {
		logger.info("From -> {}", from);
		logger.info("To -> {}", to);
		return service.getSelected(from, to);
	}

	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
	public void submitTask(@Valid @RequestBody Task task) {
		logger.info("Received new task: {}", task);
		service.save(task);
	}
}