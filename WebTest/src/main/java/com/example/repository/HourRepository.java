package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.HourList;

@Repository
public interface HourRepository extends JpaRepository<HourList, Integer> {
	 @Query("SELECT h FROM HourList h ORDER BY h.id") //ascは昇順／descは降順
	    List<HourList> findAllOrderById();
}
