package ua.skillsup.practice.restworkshop.repositories.entities;

import javax.persistence.*;

@Entity
public class SubTaskEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	@Column(name = "poryadok")
	private int order;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "task_id")
	private TaskEntity task;

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

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public TaskEntity getTask() {
		return task;
	}

	public void setTask(TaskEntity task) {
		this.task = task;
	}

	@Override
	public String toString() {
		return "SubTaskEntity{" +
				"id=" + id +
				", title='" + title + '\'' +
				", order=" + order +
				'}';
	}
}