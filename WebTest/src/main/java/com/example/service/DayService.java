package com.example.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DayList;
import com.example.repository.DayRepository;

@Service
@Transactional
@ComponentScan("com.example.repository")
public class DayService{
	@Autowired
	private DayRepository dayRepository;
	
	public List<DayList> findAll() {
        return dayRepository.findAll();
    }
	public DayList getId(Integer id) {
        return dayRepository.findById(id).orElse(null);
    }

	public void saveDays(LocalDate days, Integer id) {
        DayList dayList = new DayList();
        dayList.setId(id);
        dayList.setDays(days);
        dayRepository.save(dayList);
    }

	public void save(DayList dayList) {
		dayRepository.save(dayList);
	}
}
