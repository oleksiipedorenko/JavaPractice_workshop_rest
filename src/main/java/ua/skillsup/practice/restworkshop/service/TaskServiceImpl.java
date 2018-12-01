package ua.skillsup.practice.restworkshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.skillsup.practice.restworkshop.model.Task;
import ua.skillsup.practice.restworkshop.repository.TaskRepository;
import ua.skillsup.practice.restworkshop.repository.entity.TaskEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TaskServiceImpl implements TaskService {

	private final TaskRepository taskRepository;

	@Autowired
	public TaskServiceImpl(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public List<Task> getSelected(LocalDate from, LocalDate to) {
		if (Objects.isNull(from) && Objects.isNull(to)) {
			return StreamSupport.stream(taskRepository.findAll().spliterator(), false)
					.map(TaskServiceImpl::fromEntity)
					.collect(Collectors.toList());
		} else if (Objects.nonNull(from) && Objects.isNull(to)) {
			return taskRepository.findByFinishTimeAfter(
					LocalDateTime.of(from, LocalTime.MIDNIGHT)
			).stream()
					.map(TaskServiceImpl::fromEntity)
					.collect(Collectors.toList());
		}

		return Collections.emptyList();
	}

	@Override
	public void save(Task task) {
		TaskEntity entity = new TaskEntity();
		entity.setTitle(task.getTitle());
		entity.setDescription(task.getDescription());
		entity.setFinishTime(task.getFinishTime());
		taskRepository.save(entity);
	}

	private static Task fromEntity(TaskEntity entity) {
		return new Task(
				entity.getTitle(),
				entity.getDescription(),
				entity.getFinishTime(),
				Collections.emptyList()
		);
	}
}
