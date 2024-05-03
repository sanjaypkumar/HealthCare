package in.project.sanjay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.project.sanjay.entity.Doctor;
import in.project.sanjay.exception.DoctorNotFoundException;
import in.project.sanjay.repo.DoctorRepository;
import in.project.sanjay.service.IDoctorService;

@Service
public class DoctorServiceImpl implements IDoctorService {

	@Autowired
	private DoctorRepository repo;
	
	@Override
	public Long saveDoctor(Doctor doc) {
		// TODO Auto-generated method stub
		return repo.save(doc).getId();
	}

	@Override
	public List<Doctor> getAllDoctor() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public void removeDoctor(Long id) {
		// TODO Auto-generated method stub
		repo.delete(getOneDoctor(id));
	}

	@Override
	public Doctor getOneDoctor(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElseThrow(
				()-> new DoctorNotFoundException(id + ", Not Exist")
				);
	}

	@Override
	public void updateDoctor(Doctor doc) {
		// TODO Auto-generated method stub
		if(repo.existsById(doc.getId())) {
			repo.save(doc);
		}else {
			throw new DoctorNotFoundException(doc.getId() + ", Not Exist");
		}
		
	}

}
