package in.project.sanjay.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.project.sanjay.entity.Doctor;
import in.project.sanjay.exception.DoctorNotFoundException;
import in.project.sanjay.service.IDoctorService;
import in.project.sanjay.service.ISpecializationService;
//import in.project.sanjay.util.FileUploadUtil;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	private IDoctorService service;

	@Autowired
	private ISpecializationService specializationService;

	/*
	 * A common methods which sends data to create Dynamic DropDown at UI in
	 * Register, Edit Pages call this methods inside controller #method where those
	 * returns ____Register.html or _____Edit.html
	 */

	private void commonDynamicUi(Model model) {
		Map<Long, String> specializations = specializationService.getSpecIdAndName();
		model.addAttribute("specializations", specializations);
	}

	/*
	 * private void createDynamicUi(Model model) {
	 * model.addAttribute("specializations",specializationService.getSpecIdAndName()
	 * ); }
	 * 
	 * 
	 * Map<Long, String> specializations = specializationService.getSpecIdAndName();
	 * model.addAttribute("specialization", specializations);
	 */

	// 1.show Register Page
	@GetMapping("/register")
	public String showRegister(@RequestParam(value = "message", required = false) String message, Model model) {
		model.addAttribute("message", message);
		commonDynamicUi(model);
		return "DoctorRegister";
	}

	// 2.Save On Submit
	@PostMapping("/save")
	public String saveForm(@ModelAttribute Doctor doctor, RedirectAttributes attributes) {
		Long id = service.saveDoctor(doctor);
		attributes.addAttribute("message", "Doctor (" + id + ")is created");
		return "redirect:register";
		/*
		 * //String fileName =
		 * StringUtils.cleanPath(multipartFile.getOriginalFilename());
		 * //doctor.setPhotos(fileName);
		 * 
		 * String uploadDir = "user-photos/" + id; try {
		 * FileUploadUtil.saveFile(uploadDir, fileName, multipartFile); } catch
		 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */
	}

	// 3.Display Data
	@GetMapping("/all")
	public String display(Model model,@RequestParam(value = "message", required = false)String message
			) 
	{
		List<Doctor> list = service.getAllDoctor();
		model.addAttribute("list", list);
		model.addAttribute("message", message);
		return "DoctorData";
	}

	/*
	 * @GetMapping("/doctorData") public String getDoctorData(Model model) { //
	 * Assuming you have a Doctor object with a Specialization object Doctor doctor
	 * = new Doctor(); doctor.setSpecialization(new Specialization("Cardiology"));
	 * model.addAttribute("ob", doctor); // Assuming "ob" is your object name return
	 * "DoctorData"; }
	 */
	// 4.delete By id
	@GetMapping("/delete")
	public String delete(@RequestParam("id") Long id, RedirectAttributes attributes) {
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

	// 5.show Edit
	@GetMapping("/edit")
	public String showEditPage(@RequestParam("id") Long id, Model model, RedirectAttributes attributes) {
		String page = null;
		try {
			Doctor doc = service.getOneDoctor(id);
			model.addAttribute("doctor", doc);
			commonDynamicUi(model);
			page = "DoctorEdit";
		} catch (DoctorNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
			page = "redirect:all";
		}
		return page;
	}

	// 6. To Update
	@PostMapping("/update")
	public String doUpdate(@ModelAttribute Doctor doctor, RedirectAttributes attributes) {
		service.updateDoctor(doctor);
		attributes.addAttribute("message", doctor.getId() + ", is Updated!");
		return "redirect:all";
	}

	// 7.email and mobile duplication validations(AJAX)

	// 8.Excel Export
}
