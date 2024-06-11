package in.project.sanjay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.project.sanjay.entity.Appointment;
import in.project.sanjay.repo.AppointmentRepository;
import in.project.sanjay.service.IAppointmentService;

@Service
public class AppointmentServiceImpl implements IAppointmentService {

	@Autowired
	private AppointmentRepository repo;
	
	@Override
	public Long saveAppointment(Appointment appointment) {
		// TODO Auto-generated method stub
		return repo.save(appointment).getId();
	}

	@Override
	public void updateAppointment(Appointment appointment) {
		// TODO Auto-generated method stub

		repo.save(appointment);
	}

	@Override
	public void deleteAppointment(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public Appointment getOneAppointment(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id).get();
	}

	@Override
	public List<Appointment> getAllAppointments() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

}
