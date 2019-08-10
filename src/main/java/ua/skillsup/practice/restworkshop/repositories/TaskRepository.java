package ua.skillsup.practice.restworkshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.skillsup.practice.restworkshop.repositories.entities.TaskEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

	List<TaskEntity> findByFinishTimeBetween(LocalDateTime from, LocalDateTime to);

	List<TaskEntity> findByFinishTimeAfter(LocalDateTime from);

	List<TaskEntity> findByFinishTimeBefore(LocalDateTime to);
}