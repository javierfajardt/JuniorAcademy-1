package com.everis.dar.junior.academy.ms.todo.list;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {

    /**
     * MVC
     */
    private MockMvc mvc;

    /**
     * Mocked Repository.
     */
    @Mock
    private TaskRepository taskRepository;

    /**
     * Controller to test.
     */
    @InjectMocks
    private TaskController taskController;

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

    @BeforeEach
    public void setUp() {
		this.task = new Task();
		this.task.setDescription(this.taskDescription);
		this.task.setStatus(this.taskStatus);
    	JacksonTester.initFields(this, new ObjectMapper());
    	this.mvc = MockMvcBuilders.standaloneSetup(this.taskController)
                .build();
    }

    /**
     * Test if the service exposed by the controller used to find all tasks works.
     * @throws Exception
     */
    @Test
    public void getTasks() throws Exception {

    	final List<Task> tasks = new ArrayList<Task>();
    	tasks.add(this.task);

    	// given
    	given(this.taskRepository.findAll()).willReturn(tasks);

    	// find at least one element
    	this.mvc.perform( MockMvcRequestBuilders
    		      .get("/tasks")
    		      .accept(MediaType.APPLICATION_JSON))
    		      .andDo(print())
    		      .andExpect(status().isOk())
    		      .andExpect(MockMvcResultMatchers.jsonPath("$..description").exists());

    }


    /**
     * Test if the service exposed by the controller used to find tasks by id works.
     * @throws Exception
     */
    @Test
    public void getTaskById() throws Exception {

    	// given
    	given(this.taskRepository.findById(3L)).willReturn(
    			Optional.of(new Task(3L, this.taskDescription, this.taskStatus)));

    	// find at least one element
    	this.mvc.perform( MockMvcRequestBuilders
    		      .get("/taskById?id=3")
    		      .accept(MediaType.APPLICATION_JSON))
    		      .andDo(print())
    		      .andExpect(status().isOk())
    		      .andExpect(MockMvcResultMatchers.jsonPath("$..description").exists());

    }

}
