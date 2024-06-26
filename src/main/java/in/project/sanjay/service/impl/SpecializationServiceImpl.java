package in.project.sanjay.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.project.sanjay.entity.Specialization;
import in.project.sanjay.exception.SpecializationNotFoundExecption;
import in.project.sanjay.repo.SpecializationRepository;
import in.project.sanjay.service.ISpecializationService;
import in.project.sanjay.util.MyCollectionsUtil;

@Service
public class SpecializationServiceImpl implements ISpecializationService {

	@Autowired
	private SpecializationRepository repo;

	@Override
	public Long saveSpecialization(Specialization spec) {
		// TODO Auto-generated method stub

		return repo.save(spec).getId();
	}

	@Override
	public List<Specialization> getAllSpecializations() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public void removeSpecialization(Long id) {
		// TODO Auto-generated method stub
		// repo.deleteById(id);
		repo.delete(getOneSpecialization(id));
	}

	@Override
	public Specialization getOneSpecialization(Long id) {
		// TODO Auto-generated method stub
		/*
		 * Optional<Specialization> optional = repo.findById(id);
		 * if(optional.isPresent()) { return optional.get(); }else { throw new
		 * SpecializationNotFoundExecption(id+ " Not Found"); }
		 */
		return repo.findById(id).orElseThrow(() -> new SpecializationNotFoundExecption(id + " Not Found"));
	}

	@Override
	public void updateSpecialization(Specialization spec) {
		// TODO Auto-generated method stub

		repo.save(spec);
	}

	@Override
	public boolean isSpecCodeExist(String specCode) {
		// TODO Auto-generated method stub
		/*
		 * Integer count = repo.getSpecializationSpecCodeCount(specCode); boolean exist
		 * = count>0? true : false; return exist;
		 */

		return repo.getSpecializationSpecCodeCount(specCode) > 0;
	}

	

	
	 @Override
	 public boolean isSpecCodeExistForEdit(String specCode, Long id) {
	  // TODO Auto-generated method stub
		 return repo.getSpecializationForEdit(specCode, id)>0; 
	}
	 

	@Override
	public Map<Long, String> getSpecIdAndName() {
		// TODO Auto-generated method stub
		List<Object[]> list = repo.getSpecIdAndName();
		Map<Long, String> map = MyCollectionsUtil.convertToMap(list);
		return map;
	}

//	@Override
//	public boolean isSpecNameExist(String specName) {
//		// TODO Auto-generated method stub
//		Integer count = repo.getSpecializationSpecNameCount(specName);
//		boolean exist = count > 0;
//		return exist;
//	}

}
