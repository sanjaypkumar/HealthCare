package in.project.sanjay.service;

import java.util.List;

import in.project.sanjay.entity.Patient;

public interface IPatientService {

	Long savePatient(Patient patient);
	
	void updatePatient(Patient patient);
	
	void deletePatient(Long id);
	
	Patient getOnePatient(Long id);
	
	List<Patient> getAllPatient();
	
	Patient getOneByEmail(String email);
	
}
