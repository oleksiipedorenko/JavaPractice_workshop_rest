package ua.skillsup.practice.restworkshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.skillsup.practice.restworkshop.repositories.entities.SubTaskEntity;

public interface SubTaskRepository extends JpaRepository<SubTaskEntity, Long> {
}
