package ua.skillsup.practice.restworkshop.model;

import java.time.LocalDateTime;
import java.util.List;

public class Task {

	private String title;
	private String description;
	private LocalDateTime finishTime;
	private List<SubTask> subTasks;

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