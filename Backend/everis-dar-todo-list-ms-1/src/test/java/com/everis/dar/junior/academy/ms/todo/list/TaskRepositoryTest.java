package com.everis.dar.junior.academy.ms.todo.list;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 * Class to test repository access.
 * @author everis
 *
 */
@DataJpaTest
public class TaskRepositoryTest {

	/**
	 * Object used to access task data from the repository.
	 */
	@Autowired
	private TaskRepository taskRepository;

	/**
	 * Objet used to store in the database.
	 */
	private Task task;

	/**
	 * Task description used for testing.
	 */
	private final String taskDescription = "Task test";

	/**
	 * Task status used for testing.
	 */
	private final String taskStatus = "Pending";

	/**
	 * Method executed before each tests and inicialices Task variable.
	 */
	@BeforeEach
	public void setUp() {
		this.task = new Task(1L, this.taskDescription, this.taskStatus);
	}

	/**
	 * Method to test save action.
	 */
	@Test
	public void saveTask() {
		final Task newTask = this.taskRepository.save(this.task);
		assertThat(newTask.getDescription())
			.isEqualTo(this.taskDescription);
	}

	/**
	 * Method to test save action.
	 */
	@Test
	public void removeTask() {
		final Task newTask = this.taskRepository.save(this.task);
		final Boolean isSaved = this.taskRepository.
				existsById(this.task.getId());
		assertThat(isSaved).isTrue();
		this.taskRepository.delete(this.task);
		final Boolean exists = this.taskRepository.
				existsById(this.task.getId());
		assertThat(exists).isFalse();
	}


	/**
	 * Method to test save action.
	 */
	@Test
	public void updateTask() {
		final Task newTask = this.taskRepository.save(this.task);
		assertThat(newTask.getDescription())
		.isEqualTo(this.taskDescription);
		newTask.setDescription(this.taskDescription + " UPDATED ");
		newTask.setStatus(this.taskStatus + " UPDATED ");
		final Task updatedTask = this.taskRepository.save(newTask);
		assertThat(newTask.getDescription())
		.isEqualTo(this.taskDescription + " UPDATED ");
		assertThat(newTask.getStatus())
		.isEqualTo(this.taskStatus + " UPDATED ");
	}

}
