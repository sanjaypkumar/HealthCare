package in.project.sanjay.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.project.sanjay.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
