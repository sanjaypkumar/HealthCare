package in.project.sanjay.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.project.sanjay.entity.Doctor;
import in.project.sanjay.exception.DoctorNotFoundException;
import in.project.sanjay.service.IDoctorService;
import in.project.sanjay.util.FileUploadUtil;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	private IDoctorService service;
	
	//1.show Register Page
	@GetMapping("/register")
	public String showRegister(
			@RequestParam(value = "message", required = false) String message,
			Model model
			) 
	{
		model.addAttribute("message", message);
		return "DoctorRegister";
	}
	
	//2.Save On Submit
	@PostMapping("/save")
	public String saveForm(
			@ModelAttribute Doctor doctor,
			@RequestParam("docImg") MultipartFile multipartFile,
			//Model model,
			RedirectAttributes attributes
			) 
	{
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename()); 
		doctor.setPhotos(fileName);
		Long id = service.saveDoctor(doctor);
		attributes.addAttribute("message", "Doctor ("+id+")is created");
		String uploadDir = "user-photos/" + id;
		try {
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:register";
	}
	
	//3.Display Data
	@GetMapping("/all")
	public String display(
			@RequestParam(value = "message", required = false) String message,
			Model model
			)
	{
		List<Doctor> list = service.getAllDoctor();
		model.addAttribute("list", list);
		model.addAttribute("message", message);
		return "DoctorData";
	}
	
	//4.delete By id
	@GetMapping("/delete")
	public String delete(
			@RequestParam Long id,
			RedirectAttributes attributes
			) 
	{
		String message = null;
		try {
			service.removeDoctor(id);
			message = "Doctor Removed";
		} catch (DoctorNotFoundException e) {
			// TODO: handle exception
			message = "Doctor Not Found";
			message = e.getMessage();
			e.printStackTrace();
		}

		attributes.addAttribute("message", message);
		return "redirect:all";
	}
	
	//5.show Edit
	@GetMapping("/edit")
	public String showEditPage(
			@RequestParam Long id,
			Model model,
			RedirectAttributes attributes
			) 
	{
		String page = null;
		try {
			Doctor doc = service.getOneDoctor(id);
			model.addAttribute("doctor", doc);
			page = "DoctorEdit";
		} catch (DoctorNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
			page = "redirect:all";
		}
		return page;
	}
	
	//6. To Update
	@GetMapping("/update")
	public String doUpdate(
			@ModelAttribute Doctor doctor,
			RedirectAttributes attributes
			) 
	{
		service.updateDoctor(doctor);
		attributes.addAttribute("message", doctor.getId()+ ", is Updated!");
		return "redirect:all";
	}
	
	//7.email and mobile duplication validations(AJAX)
	
	//8.Excel Export
}
