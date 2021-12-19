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
	@Column(name = "name", unique = true)
	private String name;

	@Column(name = "username", unique = true)
	private String username;

	@Column(name = "password", unique = true)
	private String password;

	@Column(name = "bilkentId", unique = true)
	private Integer bilkentId;

	@Getter @Setter
	@Column(name = "age", unique = true)
	private Integer age;

	@Column(name = "dateOfBirth", unique = true)
	private String dateOfBirth;

	@Column(name = "phoneNumber", unique = true)
	private String phoneNumber;

	/*@Column(name = "TestResult", unique = true)
	private UUID testResult;

	@Column(name = "notifications", unique = true)
	private UUID notifications;

	@Column(name = "exReport", unique = true)
	private UUID exReport;

	@Column(name = "covidInfo", unique = true)
	private UUID covidInfo;

	@Column(name = "appointments", unique = true)
	private UUID appointments;0*/

	//Methods
	/*public void addAppointment(Appointment appointment){}
	public String getRiskStatus(){};
	public VaccinationInformation getVaccinationInfo(){}
	public addTestResult( TestResult testResult)(){}*/



}
