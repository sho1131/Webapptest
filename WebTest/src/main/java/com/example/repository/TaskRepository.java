package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
	 @Query("SELECT t FROM Task t ORDER BY t.id") //ascは昇順／descは降順
	    List<Task> findAllOrderById();
}
