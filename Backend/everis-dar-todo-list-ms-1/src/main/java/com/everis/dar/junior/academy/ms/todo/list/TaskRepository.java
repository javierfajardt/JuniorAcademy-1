package com.everis.dar.junior.academy.ms.todo.list;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface that extends CrudRepository, used to manage web requests.
 * @author everis
 *
 */
@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

}
