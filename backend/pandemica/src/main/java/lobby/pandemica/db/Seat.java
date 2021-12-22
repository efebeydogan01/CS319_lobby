package lobby.pandemica.db;

import lobby.pandemica.db.base.BaseEntity;
import lobby.pandemica.dto.SectionDto;
import lobby.pandemica.dto.StudentDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "seats")
public class Seat extends BaseEntity {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @Column(name = "exists", nullable = false)
    private Boolean exists;

    @Column(name = "row", nullable = false)
    private Integer row;

    @Column(name = "column", nullable = false)
    private Integer column;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    private Section section;
}