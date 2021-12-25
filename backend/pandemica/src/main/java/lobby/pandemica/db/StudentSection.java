package lobby.pandemica.db;

import lobby.pandemica.db.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "student_section",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"student_id", "section_id"}
                )
        }
)
/**
 * Data class for encapsulating student with its section, creating a many to many relation
 */
public class StudentSection extends BaseEntity
{
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    private Section section;
}
