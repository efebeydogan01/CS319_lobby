package lobby.pandemica.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lobby.pandemica.db.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "academic_personnel")
@Entity
/**
 * Entity class for academic personnel, which delegates a corresponding user entity
 */
public class AcademicPersonnel extends BaseEntity {
    @Column(name = "department", nullable = false)
    private String department;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

//    @JsonIgnore
//    @OneToMany(mappedBy = "academicPersonnel")
//    private Set<Section> sections = new HashSet<>();
}
