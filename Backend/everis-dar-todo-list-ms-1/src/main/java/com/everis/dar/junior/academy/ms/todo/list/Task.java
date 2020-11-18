package com.everis.dar.junior.academy.ms.todo.list;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * This class is used to represent and instantiate the different tasks of the to-do list.
 * @author dgutiema
 *
 */

@Entity
public class Task {

	@Id
	private Long id;

	private final String description;
	private final String status;

	public Task() {
		super();
		this.description = "";
		this.status = "";
	}

	public Task(Long id, String description, String status) {
		super();
		this.id = id;
		this.description = description;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public String getStatus() {
		return status;
	}

}
