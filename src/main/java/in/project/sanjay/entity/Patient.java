package in.project.sanjay.entity;

import java.util.Date;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "patient_tab")
public class Patient {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name="pat_fn_col")
	private String firstName;
	
	@Column(name="pat_ln_col")
	private String lastName;
	
	@Column(name="pat_gen_col")
	private String gender;
	
	@Column(name="pat_mob_col")
	private String mobile;
	
	@Column(name="pat_email_col")
	private String email;
	
	@Column(name="pat_dob_col")
	@DateTimeFormat(iso = ISO.DATE)
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
	@Column(name="pat_ms_col")
	private String marialStatus;
	
	@Column(name="pat_paddr_col")
	private String presentAddr;
	
	@Column(name="pat_caddr_col")
	private String commAddr;
	
	@ElementCollection
	@CollectionTable(
			name = "pat_madi_hist_tab",
		joinColumns = @JoinColumn(name = "pat_madi_hist_id_fk_col")
	)
	@Column(name = "pat_medi_hist_col")
	private Set<String> medicalHistory;
	
	@Column(name="pat_other_col")
	private String ifOther;
	
	@Column(name="pat_note_col")
	private String note;
}
