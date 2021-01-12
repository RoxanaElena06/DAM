package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.javaguides.springboot.model.Task;
import net.javaguides.springboot.service.TaskService;

@Controller("/taskController")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@GetMapping("/task.html")
	public String viewHomePage(Model model) {
		return findPaginatedTask(1, "title", "asc", model);		
	}	
	@GetMapping("/showTaskForm")
	public String showTaskForm(Model model) {
		// create model attribute to bind form data
		Task task = new Task();
		model.addAttribute("task", task);
		return "task";
	}
	
	@GetMapping("/showNewTaskForm")
	public String showNewTaskForm(Model model) {
		// create model attribute to bind form data
		Task task = new Task();
		model.addAttribute("task", task);
		return "new_task.html";
	}
	
	@PostMapping("/saveTask")
	public String saveTask(@ModelAttribute("task") Task task) {
		// save task to database
		taskService.saveTask(task);
		return "redirect:/task.html";
	}
	
	
	@GetMapping("/showFormForUpdateTask/{id}")
	public String showFormForUpdateTask(@PathVariable ( value = "id") long id, Model model) {
		
		// get task from the service
		Task task= taskService.getTaskById(id);
		
		// set task as a model attribute to pre-populate the form
		model.addAttribute("task", task);
		return "update_task.html";
	}
	
	@GetMapping("/deleteTask/{id}")
	public String deleteTask(@PathVariable (value = "id") long id) {
		
		// call delete task method 
		this.taskService.deleteTaskById(id);
		return "redirect:/task.html";
	}
	
	
	@GetMapping("/task/page/{pageNo}")
	public String findPaginatedTask(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Task> page = taskService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Task> listTasks = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listTasks", listTasks);
		return "task";
	}
}
