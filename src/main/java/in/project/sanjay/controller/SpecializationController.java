package in.project.sanjay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.project.sanjay.entity.Specialization;
import in.project.sanjay.execption.SpecializationNotFoundExecption;
import in.project.sanjay.service.ISpecializationService;
import in.project.sanjay.view.SpecializationExcelView;

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
		try {
			service.removeSpecialization(id);
			attributes.addAttribute("message", "Record ("+id+") is removed");
		} catch (SpecializationNotFoundExecption e) {
			// TODO: handle exception
			attributes.addAttribute("message", e.getMessage());
			e.printStackTrace();
		}
		
		return "redirect:all"; 
	}
	
	/*
	 * 5.Fetch Data into Edit page
	 * 	 End user  clicks on link, May  enter ID manually,
	 * 	 If entered id is presented in DB
	 * 		>	Load row as Object
	 * 		>	send to edit page
	 * 		> 	fill the form
	 * 	 ELSE
	 * 		> 	Redirect to all (Data Page)
	 * 		>	show error message(not found)
	 */
	@GetMapping("/edit")
	public String showEditPage(
			@RequestParam Long id,
			Model model,
			RedirectAttributes attributes
			) 
	{
		String page = null;
		try {
			Specialization spec = service.getOneSpecialization(id);
			model.addAttribute("specialization", spec);
			page = "SpecializationEdit";
		} catch (SpecializationNotFoundExecption e) {
			// TODO: handle exception
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
			page = "redirect:all";
		}
		
		return page;
	}
	
	/*
	 * 6. Update Form data redirect to all
	 */
	@PostMapping("/update")
	public String updateData(
			@ModelAttribute Specialization specialization,
			RedirectAttributes attributes
			)
	{
		service.updateSpecialization(specialization);
		attributes.addAttribute("message","Record ("+specialization.getId()+") is Updated");
		return "redirect:all";
	}
	
	/*
	 * 7. read code and check with service
	 *    Return message back to UI
	 */
	@GetMapping("/checkCode")
	@ResponseBody
	public String validateSpecCode(
			@RequestParam String code
			) 
	{
		String message="";
		if(service.isSpecCodeExist(code)){
			message = code +" , *Already exist";
		}
		return message; //This time its not a viewName its a message
	}
	
	/*
	 * 8. Read Name and check with service
	 *    Return message back to UI
	 */
	@GetMapping("/checkName")
	@ResponseBody
	public String validateSpecName(
			@RequestParam String name
			) 
	{
		String message="";
		if(service.isSpecNameExist(name)) {
			message = name +", *Already Exist";
		}
		return message;  //This time its not a viewname(its a message)
	}
	
	/*
	 * 8. Export Data to Excel file
	 */
	@GetMapping("/excel")
	public ModelAndView exportToExcel() {
		ModelAndView m = new ModelAndView();
		m.setView(new SpecializationExcelView());
		//Read data from DB
		List<Specialization> list = service.getAllSpecializations();
		//Send Data to Excel Impl class
		m.addObject("list", list);
		return  m;
	}
}
