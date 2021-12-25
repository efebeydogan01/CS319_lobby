package lobby.pandemica.db;

import lobby.pandemica.db.base.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "seats",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"section_id", "row_no", "column_no"}
                )
        }
)
/**
 * Entity class of requestForm sent by users, includes the sender and text
 */
public class Seat extends BaseEntity {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @Column(name = "exists", nullable = false)
    private Boolean exists;

    @Column(name = "row_no", nullable = false)
    private Integer row;

    @Column(name = "column_no", nullable = false)
    private Integer column;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    private Section section;
}