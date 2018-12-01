package ua.skillsup.practice.restworkshop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

public class Task {

	@NotNull
	@Size(min = 3, message = "Use at least 3 symbols for title!!!!!")
	private String title;
	private String description;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime finishTime;
	private List<SubTask> subTasks;

	public Task() {
		//for jackson
	}

	public Task(@NotNull @Size(min = 3, message = "Use at least 3 symbols for title!!!!!") String title,
	            String description, LocalDateTime finishTime, List<SubTask> subTasks) {
		this.title = title;
		this.description = description;
		this.finishTime = finishTime;
		this.subTasks = subTasks;
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

	public List<SubTask> getSubTasks() {
		return subTasks;
	}

	public void setSubTasks(List<SubTask> subTasks) {
		this.subTasks = subTasks;
	}

	@Override
	public String toString() {
		return "Task{" +
				"title='" + title + '\'' +
				", description='" + description + '\'' +
				", finishTime=" + finishTime +
				", subTasks=" + subTasks +
				'}';
	}
}