package com.everis.dar.junior.academy.ms.todo.list;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * This class is used to represent and instantiate the different tasks
 * of the to-do list.
 * @author everis
 */
@Entity
public class Task {

	/**
	 * Task Unike ID.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Task description.
	 */
	private String description;

	/**
	 * Task status. Its necesary to define the values that it could take.
	 */
	private String status;

	/**
	 * Default constructor without parameters.
	 */
	public Task() {
		super();
		this.description = "";
		this.status = "";
	}

	/**
	 * Task constructor with parameters.
	 * @param id Initial Task ID.
	 * @param description Task Description.
	 * @param status Current Task Status.
	 */
	public Task(final Long id, final String description,
			final String status) {
		super();
		this.id = id;
		this.description = description;
		this.status = status;
	}

	/**
	 * Task ID Getter.
	 * @return ID.
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Task Description Getter.
	 * @return Task Description.
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Task Status Getter.
	 * @return Task Status.
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * Set Description to the current Task.
	 * @param description New description value.
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Set Status to the current Task.
	 * @param status New Status value.
	 */
	public void setStatus(final String status) {
		this.status = status;
	}

}
