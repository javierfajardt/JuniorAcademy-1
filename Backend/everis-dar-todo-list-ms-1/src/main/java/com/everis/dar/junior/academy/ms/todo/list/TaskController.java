package com.everis.dar.junior.academy.ms.todo.list;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class used to control the different requests related to
 * our tasks (get,post,put,delete).
 * @author everis.
 *
 */
@CrossOrigin(origins = "*")
@RestController
public class TaskController {

	/**
	 * Object used to access task data from the repository.
	 */
	@Autowired
	private TaskRepository taskRepository;

	/**
	 * Service used to list all the tasks.
	 * @return All the tasks.
	 */
	@GetMapping("/tasks")
	public List<Task> getTasks() {
		return (List<Task>) this.taskRepository.findAll();
	}

	/**
	 * Service used to find one task in the database using its ID.
	 * @param id Task ID searched.
	 * @return Task Object.
	 */
	@GetMapping("/taskById")
	public Optional<Task> getTaskById(@RequestParam final Long id) {
		return this.taskRepository.findById(id);
	}

	/**
	 * Service used to create new tasks.
	 * @param task New task. ID param is not necesary.
	 * @return New task stored including the generated ID.
	 */
	@PostMapping("/tasks")
	public Task addTask(@RequestBody final Task task) {
		return this.taskRepository.save(task);
	}

	/**
	 * Service used to update an existing task.
	 * @param task existing task with the new data.
	 * @return Updated task stored including updated data.
	 */
	@PutMapping("/tasks")
	public Task updateTask(@RequestBody final Task task) {
		return this.taskRepository.save(task);
	}

	/**
	 * Service used for delete an existing Task.
	 * @param task Task to delete.
	 */
	@DeleteMapping("/tasks")
	public void deleteTask(@RequestBody final Task task) {
		this.taskRepository.delete(task);
	}

	/**
	 * Service used to delete a task using its ID.
	 * @param id
	 */
	@DeleteMapping("/taskById")
	public void deleteTaskbyId(@RequestParam final Long id) {
		this.taskRepository.deleteById(id);
	}

	/**
	 * Service used to delete all tasks from the repository.
	 */
	@DeleteMapping("/allTasks")
	public void deleteAll() {
		this.taskRepository.deleteAll();
	}

}
