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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.project.sanjay.entity.Appointment;
import in.project.sanjay.exception.AppointmentNotFoundExecption;
import in.project.sanjay.service.IAppointmentService;
import in.project.sanjay.service.IDoctorService;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

	@Autowired
	private IAppointmentService service;
	
	@Autowired
	private IDoctorService doctorService;
	
	private void commonUi(Model model) {
		model.addAttribute("doctors", doctorService.getDoctorIdAndNames());
	}
	
	@GetMapping("/register")
	public String registerAppoiontment(Model model) {
		model.addAttribute("appointment", new Appointment());
		commonUi(model);
		return "AppointmentRegister";
	}
	
	@PostMapping("/save")
	public String saveAppointment(@ModelAttribute Appointment appointment, Model model) {
		java.lang.Long id = service.saveAppointment(appointment);
		model.addAttribute("message","Appointment created with id: " +id);
		model.addAttribute("appointment", new Appointment());
		commonUi(model);
		return "AppointmentRegister";
	}
	
	@GetMapping("/all")
	public String getAllAppointments(Model model,
			@RequestParam(value = "message", required = false) String message) 
	{
		List<Appointment> list = service.getAllAppointments();
		model.addAttribute("list", list);
		model.addAttribute("message", message);
		return "AppointmentData";
	}
	
	@GetMapping("/delete")
	public String deleteAppointment(@RequestParam Long id, RedirectAttributes attributes) {
		try {
			service.deleteAppointment(id);
			attributes.addAttribute("message", "Appointment deleted with id: "+id);
		} catch (AppointmentNotFoundExecption e) {
			// TODO: handle exception
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
		}
		return "redirect:all";
	}
	
	@GetMapping("/edit")
	public String editAppointment(@RequestParam Long id, Model model, RedirectAttributes attributes) {
		String page = null;
		try {
			Appointment aop = service.getOneAppointment(id);
			model.addAttribute("appointment", aop);
			commonUi(model);
			page = "AppointmentEdit";
		} catch (AppointmentNotFoundExecption e) {
			// TODO: handle exception
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
			page = "redirect:all";
		}
		return page;
	}
	
	@PostMapping("/update")
	public String updateAppointment(@ModelAttribute Appointment appointment, 
			RedirectAttributes attributes) {
		service.updateAppointment(appointment);
		attributes.addAttribute("message", "appointment updated");
		return "redirect:all";
	}
}
