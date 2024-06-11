package in.project.sanjay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.project.sanjay.entity.Patient;
import in.project.sanjay.exception.DoctorNotFoundException;
import in.project.sanjay.exception.PatientNotFoundException;
import in.project.sanjay.repo.PatientRepository;
import in.project.sanjay.service.IPatientService;
import jakarta.transaction.Transactional;

@Service
public class PatientServiceImpl implements IPatientService {

	@Autowired
	private PatientRepository repo;
	
	@Override
	//@Transactional
	public Long savePatient(Patient patient) {
		// TODO Auto-generated method stub
		Long id = repo.save(patient).getId();
		/*
		 * if(id!=null) { String pwd = util.genPwd(); User user = new User();
		 * user.setDisplayName(patient.getFirstName()+" "+patient.getLastName());
		 * user.setUserName(patient.get)
		 * 
		 * }
		 */
		return id;
	}

	@Override
	//@Transactional
	public void updatePatient(Patient patient) {
		// TODO Auto-generated method stub
		//repo.save(patient);
		if(repo.existsById(patient.getId())) {
			repo.save(patient);
		}else {
			throw new PatientNotFoundException(patient.getId() + ", Not Exist");
		}
	}

	@Override
	//@Transactional
	public void deletePatient(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	//@Transactional
	public Patient getOnePatient(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id).get();
	}

	@Override
	//@Transactional
	public List<Patient> getAllPatient() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Patient getOneByEmail(String email) {
		// TODO Auto-generated method stub
		//repo.findByEmail(email).get();
		//return repo.findByEmail(email).get();
		return repo.findByEmail(email).orElseThrow(
				()-> new PatientNotFoundException(email + ", Not Exist")
				);
	}

}
