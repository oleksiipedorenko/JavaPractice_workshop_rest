package ua.skillsup.practice.restworkshop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.skillsup.practice.restworkshop.repository.entity.TaskEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<TaskEntity, Long> {

	List<TaskEntity> findByFinishTimeAfter(LocalDateTime from);
}
