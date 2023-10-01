package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.DayList;

@Repository
public interface DayRepository extends JpaRepository<DayList, Integer> {
	@Query("SELECT d FROM DayList d ORDER BY d.id") //ascは昇順／descは降順
		List<DayList> findAllOrderById();

}
