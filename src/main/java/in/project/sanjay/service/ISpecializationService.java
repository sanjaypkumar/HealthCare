package in.project.sanjay.service;

import java.util.List;
import java.util.Map;

import in.project.sanjay.entity.Specialization;

public interface ISpecializationService {

	public Long saveSpecialization(Specialization spec);
	public List<Specialization> getAllSpecializations();
	public void removeSpecialization(Long id);
	public Specialization getOneSpecialization(Long id);
	public void updateSpecialization(Specialization spec);
	
	public boolean isSpecCodeExist(String specCode);
	
	//public boolean isSpecNameExist(String specName);
	
	public boolean isSpecCodeExistForEdit(String specCode, Long id);
	
	Map<Long, String> getSpecIdAndName();
}
