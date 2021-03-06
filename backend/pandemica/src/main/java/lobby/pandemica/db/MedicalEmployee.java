package lobby.pandemica.db;

import lobby.pandemica.db.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Represents the unique attributes of a medical employee.
 * This class holds a reference to the User table, which has the common attributes of this medical employee.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "medical_employees")

public class MedicalEmployee extends BaseEntity {
    //Attributes
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "working_location", nullable = false)
    private String workingLocation;
}
