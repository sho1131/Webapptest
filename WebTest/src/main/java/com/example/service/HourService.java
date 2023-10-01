package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.HourList;
import com.example.repository.HourRepository;

@Service
@Transactional
@ComponentScan("com.example.repository")
public class HourService{
	@Autowired
	private HourRepository hourRepository;
	
	public List<HourList> findAll() {
		return hourRepository.findAll();
	}
}
