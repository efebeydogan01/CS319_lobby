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

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "neighbors")
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
