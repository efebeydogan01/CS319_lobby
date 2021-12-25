package lobby.pandemica.db;

import lobby.pandemica.db.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Represents the vaccine information of a user. This class holds a reference to its user.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(
		name = "vaccination_information",
		uniqueConstraints = {
				@UniqueConstraint(
						columnNames = {"user_id", "date"}
				)
		}
)
public class VaccineInformation extends BaseEntity
{
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	User user;

	@Column(name = "vaccine_name", nullable = false)
	String vaccineName;

	@Column(name = "date", nullable = false)
	Date date;
}
