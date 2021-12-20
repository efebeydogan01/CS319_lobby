package lobby.pandemica.db;

import lobby.pandemica.db.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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

	@Column(name = "bilkentId", unique = true, nullable = false)
	private Integer bilkentId;

	@Column(name = "dateOfBirth", nullable = false)
	private Date dateOfBirth = new Date(System.currentTimeMillis());

	@Column(name = "phoneNumber", nullable = false)
	private String phoneNumber;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "covid_information_id", referencedColumnName = "id")
	private CovidInformation covidInformation;
/*
=======


	/*
>>>>>>> Stashed changes
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
