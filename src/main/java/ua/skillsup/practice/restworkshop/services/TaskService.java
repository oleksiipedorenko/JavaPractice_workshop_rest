package ua.skillsup.practice.restworkshop.services;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.skillsup.practice.restworkshop.exceptions.NoSuchTaskException;
import ua.skillsup.practice.restworkshop.models.SubTask;
import ua.skillsup.practice.restworkshop.models.Task;
import ua.skillsup.practice.restworkshop.repositories.SubTaskRepository;
import ua.skillsup.practice.restworkshop.repositories.TaskRepository;
import ua.skillsup.practice.restworkshop.repositories.entities.SubTaskEntity;
import ua.skillsup.practice.restworkshop.repositories.entities.TaskEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

	private final TaskRepository taskRepository;
	private final SubTaskRepository subTaskRepository;

	public TaskService(TaskRepository taskRepository, SubTaskRepository subTaskRepository) {
		this.taskRepository = taskRepository;
		this.subTaskRepository = subTaskRepository;
	}

	private static Task convertFromEntity(TaskEntity entity) {
		Task task = new Task();
		task.setId(entity.getId());
		task.setTitle(entity.getTitle());
		task.setDescription(entity.getDescription());
		task.setFinishTime(entity.getFinishTime());
		task.setSubTasks(entity.getSubTasks()
				.stream()
				.map(TaskService::convertFromEntity)
				.collect(Collectors.toList())
		);
		return task;
	}

	private static SubTask convertFromEntity(SubTaskEntity entity) {
		SubTask subTask = new SubTask();
		subTask.setOrder(entity.getOrder());
		subTask.setTitle(entity.getTitle());
		return subTask;
	}

	public List<Task> getAll(LocalDate from, LocalDate to) {
		List<TaskEntity> entities;
		if (from == null && to == null) {
			entities = taskRepository.findAll();
		} else if (from == null) {
			entities = taskRepository.findByFinishTimeBefore(convert(to));
		} else if (to == null) {
			entities = taskRepository.findByFinishTimeAfter(convert(from));
		} else {
			entities = taskRepository
					.findByFinishTimeBetween(convert(from), convert(to));
		}
		return entities.stream()
				.map(TaskService::convertFromEntity)
				.collect(Collectors.toList());
	}

	private static LocalDateTime convert(LocalDate localDate) {
		return LocalDateTime.of(localDate, LocalTime.MIDNIGHT);
	}

	@Transactional
	public void store(Task task) {
		TaskEntity entity = new TaskEntity();
		entity.setTitle(task.getTitle());
		entity.setDescription(task.getDescription());
		entity.setFinishTime(task.getFinishTime());
		taskRepository.save(entity);
	}

	@Transactional
	public void addSubtask(long taskId, SubTask subTask) {
		Optional<TaskEntity> task = taskRepository.findById(taskId);
		if (!task.isPresent()) {
			throw new NoSuchTaskException("No task with ID " + taskId);
		}
		SubTaskEntity entity = new SubTaskEntity();
		entity.setTitle(subTask.getTitle());
		entity.setOrder(subTask.getOrder());
		entity.setTask(task.get());
		subTaskRepository.save(entity);
	}
}