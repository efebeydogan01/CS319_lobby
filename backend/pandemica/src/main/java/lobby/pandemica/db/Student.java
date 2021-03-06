package lobby.pandemica.db;

import lobby.pandemica.db.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Represents the unique attributes of a student.
 * This class holds a reference to the User table, which has the common attributes of this student.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")
/**
 * Entity class for student user role, delegating the corresponding user
 */
public class Student extends BaseEntity {
    //Attributes
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "year", nullable = false)
    private String year;
}
