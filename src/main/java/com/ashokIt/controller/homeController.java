package com.ashokIt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ashokIt.model.MyUser;
import com.ashokIt.model.Task;
import com.ashokIt.repository.TaskRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class homeController {
	@Autowired
	TaskRepository taskRepo;
	MyUser session_user;

	@GetMapping(path = { "/", "/home" })
	public String displayHomepage(HttpServletRequest request, Model model) {
		session_user = (MyUser) request.getSession().getAttribute("user");
		model.addAttribute("user", session_user);
		model.addAttribute("taskList", taskRepo.findByOwner_id(session_user.getId()));
		return "ViewTasks";
	}

	@GetMapping(path = { "/addTask" })
	public String displayAddTask(Task task) {
		return "AddTask";
	}

	@PostMapping(path = "/taskSubmit")
	public String FormProcessing(@Valid @ModelAttribute("task") Task task, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "AddTask";
		}
		Example<Task> exampleTask = Example.of(task);
		if (taskRepo.findAll(exampleTask).isEmpty()) {
			task.setOwner(session_user);
			taskRepo.save(task);
		} else {
			model.addAttribute("msg", "Task Already exists");
			return "AddTask";
		}
		return "redirect:";
	}

	@PostMapping("/edit{taskId}")
	public String loadEditTask(@RequestParam(required = true) Integer taskId, Model model) {
		Task task = taskRepo.findById(taskId).get();
		model.addAttribute("task", task);
		return "EditTask";
	}

	@PostMapping(path = "/taskUpdateSubmit")
	public String FormUpdateProcessing(@Valid Task task, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "EditTask";
		}

		task.setOwner(session_user);
		taskRepo.save(task);
		return "redirect:";
	}

	@PostMapping("/delete{taskId}")
	public String deleteTask(Integer taskId) {
		taskRepo.deleteById(taskId);
		return "redirect:/home";
	}

}
