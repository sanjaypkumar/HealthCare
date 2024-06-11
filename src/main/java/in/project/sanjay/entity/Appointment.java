package in.project.sanjay.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "appointment_tab")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "app_doc_id_fk_col")
	private Doctor doctor;
	
	//@DateTimeFormat(pattern="mm/dd/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "app_date_col")
	@DateTimeFormat(iso = ISO.DATE)
	private Date date;
	
	@Column(name = "app_solts_col")
	private Integer noOfSlots;
	
	@Column(name = "app_details_col")
	private String details;
	
	@Column(name = "app_fee_col")
	private Double fees;
}
