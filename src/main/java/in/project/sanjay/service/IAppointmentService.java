package in.project.sanjay.service;

import java.util.List;

import in.project.sanjay.entity.Appointment;

public interface IAppointmentService {

	Long saveAppointment(Appointment appointment);
	void updateAppointment(Appointment appointment);
	void deleteAppointment(Long id);
	Appointment getOneAppointment(Long id);
	List<Appointment> getAllAppointments();
	
}
