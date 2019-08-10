package ua.skillsup.practice.restworkshop.repositories.entities;

import ua.skillsup.practice.restworkshop.models.SubTask;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class TaskEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String description;
	private LocalDateTime finishTime;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "task")
	private List<SubTaskEntity> subTasks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(LocalDateTime finishTime) {
		this.finishTime = finishTime;
	}

	public List<SubTaskEntity> getSubTasks() {
		return subTasks;
	}

	public void setSubTasks(List<SubTaskEntity> subTasks) {
		this.subTasks = subTasks;
	}

	@Override
	public String toString() {
		return "TaskEntity{" +
				"id=" + id +
				", title='" + title + '\'' +
				", description='" + description + '\'' +
				", finishTime=" + finishTime +
				'}';
	}
}