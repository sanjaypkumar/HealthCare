package in.project.sanjay.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.project.sanjay.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}
