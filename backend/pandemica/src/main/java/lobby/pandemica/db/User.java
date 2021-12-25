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

/**
 * Represents a user, which is the common attributes of everyone.
 */
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

	@Column(name = "role")
	private String role;
}
