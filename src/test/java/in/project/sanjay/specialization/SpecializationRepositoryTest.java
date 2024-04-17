package in.project.sanjay.specialization;

//import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

//import org.junit.jupiter.api.ClassOrderer.OrderAnnotation;
//import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import in.project.sanjay.entity.Specialization;
import in.project.sanjay.repo.SpecializationRepository;

@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
//@TestMethodOrder(OrderAnnotation.class)
public class SpecializationRepositoryTest {

	@Autowired
	private SpecializationRepository repo;
	
	/*
	 * 	1.Test save operation
	 */
	//@Disabled
	@Test
	@Order(1)
	public void testSpecCreate() {
		Specialization spec = new Specialization(null, "CRDLS","Cardiologists",
				"Heart and blood vessels like heart attack");
		spec = repo.save(spec);
		assertNotNull(spec.getId(),"Spec is not created");
	}
	
	/*
	 * 2. Test Display all operation
	 */
	@Test
	//@Order(2)
	public void testSpecFetchAll() {
		List<Specialization> list = repo.findAll();
		assertNotNull(list);
		//assertThat(list.size()>0);
		if(list.isEmpty()) {
			fail("No data exist in DataBase");
		}
	}
	
}
