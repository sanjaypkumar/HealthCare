package in.project.sanjay.service;

import java.util.List;

import in.project.sanjay.entity.Doctor;

public interface IDoctorService {

	public Long saveDoctor(Doctor doc);
	public List<Doctor> getAllDoctor();
	public void removeDoctor(Long id);
	public Doctor getOneDoctor(Long id);
	public void updateDoctor(Doctor doctor);
	
	
}
