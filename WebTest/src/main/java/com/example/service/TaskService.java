package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Task;
import com.example.repository.TaskRepository;

@Service
@Transactional
@ComponentScan("com.example.repository")
public class TaskService{
	@Autowired
	TaskRepository taskRepository;
	
	public List<Task> findAll() {
        return taskRepository.findAll();
    }
	
	 public void insert(Task task) {
	        taskRepository.save(task);
	    }

	    public void update(Task task) {
	        taskRepository.save(task);
	    }

	    public void delete(Integer id) {
	        taskRepository.deleteById(id);
	    }

	    public Optional<Task> selectById(Integer id) {
	        return taskRepository.findById(id);
	    }
	
}
