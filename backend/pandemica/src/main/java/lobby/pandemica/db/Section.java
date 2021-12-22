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
        name = "sections",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"course_name", "section_no"}
        )
)
public class Section extends BaseEntity {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "academic_personnel_id", referencedColumnName = "id")
    private AcademicPersonnel academicPersonnel;

    @Column(name = "course_name", nullable = false)
    private String courseName;

    @Column(name = "section_no", nullable = false)
    private Integer sectionNo;

    @Column(name = "classroom", nullable = false)
    private String classroom;
}
