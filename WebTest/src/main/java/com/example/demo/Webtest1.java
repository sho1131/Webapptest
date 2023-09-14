package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class Webtest1 {
	
	record TaskItem(String id, String task, String deadline, boolean done) {}
	private List<TaskItem> taskItems = new ArrayList<>();
	
	@GetMapping({"/login","/restadd"})	
	public String login() {
		return "login.html";
	}
	String addItem(@RequestParam("task") String task,
				   @RequestParam("deadline") String deadline) {
		String id = UUID.randomUUID().toString().substring(0,8);
		TaskItem item = new TaskItem(id, task, deadline, false);
		taskItems.add(item);
		
		return "タスクを追加しました";
	}
	
	int hantei = 0;
	
	@PostMapping("login")
	public String login(@RequestParam("user_id") String responseUserid, Model model) {
		model.addAttribute("responseUserid", responseUserid);
		if(responseUserid.equals("admin")) {
			hantei = 1;
		}
		return "login.html";
	}

}
