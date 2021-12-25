package lobby.pandemica.db;

import com.fasterxml.jackson.databind.ser.Serializers;
import lobby.pandemica.db.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * This is a many-to-many table of Students.
 * It holds a section which the firstStudent and secondStudent stand next to each other.
 * To ease our workload, we create duplicates of these relationships so that every neighborhood is represented twice, but mirrored.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(
		name = "neighbors",
		uniqueConstraints = {
				@UniqueConstraint(
						columnNames = {"section_id", "first_student_id", "second_student_id"}
				)
		}
)
/**
 * Entity class for neighbor, involves the relation between two students, and their section
 */
public class Neighbor extends BaseEntity
{
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "section_id", referencedColumnName = "id")
	private Section section;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "first_student_id", referencedColumnName = "id")
	private Student firstStudent;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "second_student_id", referencedColumnName = "id")
	private Student secondStudent;
}
