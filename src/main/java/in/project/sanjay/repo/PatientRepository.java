package in.project.sanjay.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.project.sanjay.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

	Optional<Patient> findByEmail(String email);

}
