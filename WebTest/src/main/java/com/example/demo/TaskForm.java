package com.example.demo;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskForm {

	public Integer id;
    
	public LocalDate date;
	
	public String startHour;
    	
	public String endHour;
	
	@NotBlank
    @Size(min = 1, max = 20)
	public String name;
    
    @Size(max = 100)
    public String memo;
}
