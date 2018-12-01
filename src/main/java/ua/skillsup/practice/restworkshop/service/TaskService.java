package ua.skillsup.practice.restworkshop.service;

import ua.skillsup.practice.restworkshop.model.Task;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {

	List<Task> getSelected(LocalDate from, LocalDate to);

	void save(Task task);
}
