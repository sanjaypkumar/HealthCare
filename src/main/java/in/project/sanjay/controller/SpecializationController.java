package in.project.sanjay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.project.sanjay.entity.Specialization;
import in.project.sanjay.service.ISpecializationService;

@Controller
@RequestMapping("/spec")
//@Configuration
public class SpecializationController {

	@Autowired
	private ISpecializationService service;
	
	/*
	 * 1. show Register Page
	 */
	@GetMapping("/register")
	public String showRegister() {
		return "SpecializationRegister";
	}
	
	
	/*
	 * 2.On submit form save Data
	 */
	@PostMapping("/save")
	public String saveForm(
			@ModelAttribute Specialization specialization,
			Model model)
	{
		Long id = service.saveSpecialization(specialization);
		String message = "Record ("+id+") is created";
		model.addAttribute("message", message);
		return "SpecializationRegister";
	}
	
	
	/*
	 * 3.Display all Specializations
	 */
	@GetMapping("/all")
	public String viewAll(
			Model model,
			@RequestParam(value = "message", required=false) String message
			) 
	{
		List<Specialization> list = service.getAllSpecializations();
		model.addAttribute("list", list);
		model.addAttribute("message",message);
		return "SpecializationData";
	}
	
	/*
	 * 4. Delete By ID
	 */
	@GetMapping("/delete")
	public String deleteData(
			@RequestParam Long id,
			RedirectAttributes attributes
			) 
	{
		service.removeSpecialization(id);
		attributes.addAttribute("message", "Record ("+id+") is removed");
		return "redirect:all"; 
	}
	
	/*
	 * 5.Fetch Data into Edit page
	 */
	@GetMapping("/edit")
	public String showEditPage(
			@RequestParam Long id,
			Model model
			) 
	{
		Specialization spec = service.getOneSpecialization(id);
		model.addAttribute("specialization", spec);
		return "SpecializationEdit";
	}
}
