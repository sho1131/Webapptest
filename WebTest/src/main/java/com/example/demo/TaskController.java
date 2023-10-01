package com.example.demo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.service.DayService;
import com.example.service.HourService;
import com.example.service.TaskService;

@Controller
@ComponentScan("com.example.service")
public class TaskController {
	@Autowired
	TaskService taskService;
	@Autowired
	HourService hourService;
	@Autowired
	DayService dayService;
		
	@GetMapping("output")
	String output(Model model) {
		//タスクの全件取得
		List<Task> tasks = taskService.findAll();
		model.addAttribute("tasks", tasks);
		//フォームで選択した日付（ID=1）の取得
		DayList firstDay = dayService.getId(1);
		model.addAttribute("firstDay", firstDay);
		//日付の全件取得
		List<DayList> dayList = dayService.findAll();
		model.addAttribute("dayList", dayList);
		//時間帯の全件取得
		List<HourList> hourList = hourService.findAll();
		model.addAttribute("hourList", hourList);

		return "output";
	}

	@PostMapping("output")
	String registdays(@RequestParam("days") String daysStr,@ModelAttribute @Validated DayList dayList, BindingResult result, Model model) {
		//バリデーションエラーがあるときの処理→リダイレクトさせる
			if (result.hasErrors()) {
		    	return "redirect:/output";
		    }
		
		//バリデーションエラーがないときの処理
			LocalDate days = LocalDate.parse(daysStr);

			// ID=1の格納(フォームで選択した日付）
				DayList firstDay = new DayList();
				firstDay.setId(1);
				firstDay.setDays(days);
				dayService.save(firstDay);

			// ID=2～7の格納
				for (int id = 2; id <= 7; id++) {
					DayList nextDays = new DayList();
					nextDays.setId(id);
					nextDays.setDays(days.plusDays(id - 1));
					dayService.save(nextDays);
				}
				
				return "redirect:/output";
    }
	
	
	@GetMapping("input")	
	String input(@ModelAttribute TaskForm taskForm, Model model) {
		//時間帯Listの設定
		List<HourList> hourList = hourService.findAll();
		model.addAttribute("hourList", hourList);
		model.addAttribute("selectedValue", "09:00");

		return "input";
	}
	
	@PostMapping("input")
	String regist(@ModelAttribute @Validated TaskForm taskForm, BindingResult result, Model model) {
		//バリデーションエラーがあるときの処理
		if (result.hasErrors()) {
	    	return input(taskForm, model);
	    }

		//バリデーションエラーがないときの処理
		Task task = new Task();
	    BeanUtils.copyProperties(taskForm, task);
	    taskService.insert(task);
	    return "redirect:/input";
	}
	  

	/*
	// ログイン機能（試作中）
	@GetMapping("login")	
	public String login() {
		return "login.html";
	}
			
	@PostMapping("login")
	public String login(@RequestParam("user_id") String responseUserid, Model model) {
		model.addAttribute("responseUserid", responseUserid);
		if(responseUserid.equals("admin")) {
			hantei = 1;
		}
		return "login.html";
	}
	*/


	//参考書情報  
	/*
	@RequestMapping("/")
	
	record TaskItem(String id, String task, String deadline, boolean done) {}
	private List<TaskItem> taskItems = new ArrayList<>();

	@GetMapping("/restadd")
	String addItem(@RequestParam("task") String task,
				   @RequestParam("deadline") String deadline) {
		String id = UUID.randomUUID().toString().substring(0,8);
		TaskItem item = new TaskItem(id, task, deadline, false);
		taskItems.add(item);
		
		return "タスクを追加しました";
	}
	
	@GetMapping("/restlist")
	String listItems() {
		String result = taskItems.stream()
						.map(TaskItem::toString)
						.collect(Collectors.joining(", "));
		return result;
	}
	*/
  
}
