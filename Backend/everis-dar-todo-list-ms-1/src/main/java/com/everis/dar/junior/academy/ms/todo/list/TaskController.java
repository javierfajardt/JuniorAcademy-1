package com.everis.dar.junior.academy.ms.todo.list;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Class used to control the different requests related to our tasks (get,post,put,delete)
 * @author dgutiema
 *
 */

@RestController
public class TaskController {

	@Autowired
	private TaskRepository taskRepository;

	@GetMapping("/tasks")
	public List<Task> getTasks() {
		return (List<Task>) taskRepository.findAll();
	}
	
	@GetMapping("/taskById")
	public  Optional<Task> getTaskById(@RequestParam Long id) {
		return taskRepository.findById(id);
	}
	
	@GetMapping("/allTasks")
	public Long countTasks() {
		return taskRepository.count();
	}
	
	@PostMapping("/tasks")
	public Task addTask(@RequestBody Task task) {
		return taskRepository.save(task);
	}

	@PutMapping("/tasks")
	public Task updateTask(@RequestBody Task task) {
		return taskRepository.save(task);
	}

	@DeleteMapping("/tasks")
	public void deleteTask(@RequestBody Task task) {
		taskRepository.delete(task);
	}
	
	@DeleteMapping("/taskById")
	public void deleteTaskbyId(@RequestParam Long id) {
		taskRepository.deleteById(id);
	}
	
	@DeleteMapping("/allTasks")
	public void deleteAll() {
		taskRepository.deleteAll();
	}
	
}
