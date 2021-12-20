package lobby.pandemica.db;

import lobby.pandemica.db.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
	@Column(name = "name")
	private String name;

	@Column(name = "username", unique = true, nullable = false)
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "bilkentId", unique = true)
	private Integer bilkentId;

	@Column(name = "age")
	private Integer age;

	@Column(name = "dateOfBirth")
	private String dateOfBirth;

	@Column(name = "phoneNumber")
	private String phoneNumber;
/*
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "testResult-id", referencedColumnName = "id")
	private TestResult testResult;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "exReport-id", referencedColumnName = "id")
	private ExaminationReport exReport;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "covidInfo-id", referencedColumnName = "id")
	private CovidInformation covidInfo;*/

	//Methods
	/*public void addAppointment(Appointment appointment){}
	public String getRiskStatus(){};
	public VaccinationInformation getVaccinationInfo(){}
	public addTestResult( TestResult testResult)(){}*/
}
