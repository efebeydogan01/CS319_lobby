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

	@Column(name = "bilkent_id", unique = true, nullable = false)
	private Integer bilkentId;

	@Column(name = "date_of_birth", nullable = false)
	private Date dateOfBirth = new Date(System.currentTimeMillis());

	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;

	@OneToOne(mappedBy = "user")
	private CovidInformation covidInformation;
}
