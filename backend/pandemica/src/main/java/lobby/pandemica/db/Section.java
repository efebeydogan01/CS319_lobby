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
        name = "sections",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"course_name", "section_no"}
        )
)
public class Section extends BaseEntity {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_id", referencedColumnName = "id")
    private User user;

    @Column(name = "course_name", nullable = false)
    private String courseName;

    @Column(name = "section_no", nullable = false)
    private Integer sectionNo;

    @Column(name = "classroom", nullable = false)
    private String classroom;
}
