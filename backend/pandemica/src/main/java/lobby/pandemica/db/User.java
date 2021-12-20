package lobby.pandemica.db;

import lobby.pandemica.db.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
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

	@Column(name = "password")
	private String password;

	@Column(name = "bilkentId", unique = true)
	private Integer bilkentId;

	@Column(name = "dateOfBirth")
	private Date dateOfBirth = new Date(System.currentTimeMillis());

	@Column(name = "phoneNumber")
	private String phoneNumber;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "covid_information_id", referencedColumnName = "id")
	private CovidInformation covidInformation;
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
