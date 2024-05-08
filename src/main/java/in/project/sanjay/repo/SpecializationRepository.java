package in.project.sanjay.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.project.sanjay.entity.Specialization;

public interface SpecializationRepository extends JpaRepository<Specialization, Long> {
	
	@Query("SELECT COUNT(specCode) FROM Specialization WHERE specCode=:specCode")
	Integer getSpecializationSpecCodeCount(String specCode);
	
	/*
	 * @Query("SELECT COUNT(specName) FROM Specialization WHERE specName=:specName")
	 * Integer getSpecializationSpecNameCount(String specName);
	 */

	@Query("SELECT COUNT(specCode) FROM Specialization WHERE specCode=:specCode AND id!=:id")
	Integer getSpecializationForEdit(String specCode, Long id);
	
	@Query("SELECT id,specName FROM Specialization")
	List<Object[]> getSpecIdAndName();
}
