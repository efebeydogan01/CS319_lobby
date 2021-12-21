package lobby.pandemica.db;

import lobby.pandemica.db.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseEntity
{
	//Attributes
	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "bilkent_id", unique = true, nullable = false)
	private Integer bilkentId;

	@Column(name = "date_of_birth", nullable = false)
	private Date dateOfBirth = new Date(System.currentTimeMillis());

	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;

	@Column(name = "age")
	private Integer age;

	@Column(name = "department")
	private String department;

	@Column(name = "year")
	@Min(0)
	@Max(4)
	private Integer year;
	/*
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "testResult-id", referencedColumnName = "id")
	private TestResult testResult;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "exReport-id", referencedColumnName = "id")
	private ExaminationReport exReport;
	*/

	//Methods
	/*public void addAppointment(Appointment appointment){}
	public String getRiskStatus(){};
	public VaccinationInformation getVaccinationInfo(){}
	public addTestResult( TestResult testResult)(){}*/
}
