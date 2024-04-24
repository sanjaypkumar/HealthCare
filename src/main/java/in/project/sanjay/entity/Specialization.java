package in.project.sanjay.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="specialization_tab")
public class Specialization {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="spec_id_col")
	private Long id;
	
	@Column(
			name="spec_code_col",
			length=12,  	 // default size is 255
			nullable=false,  //default value is true, null values are accepted
			unique = true   //default value is false, duplicates are allowed
			)
	private String specCode;
	
	@Column(
			name="spec_name_col",
			length=60,		// default size is 255
			nullable=false,	//default value is true, null values are accepted
			unique = true	//default value is false, duplicates are allowed
			)
	private String specName;
	
	@Column(
			name="spec_note_col",
			length=250, 			// default size is 255
			nullable=false			//default value is true, null values are accepted
			)
	private String specNote;
}
