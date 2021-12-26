package lobby.pandemica.db;

import lobby.pandemica.db.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

/**
 * Represents a test result of a user. This class holds a reference of its user.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "test_results")
/**
 * Entity class for holding test results of a user, among with its necessary information
 */
public class TestResult extends BaseEntity {
    //Attributes
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "test_date", nullable = false)
    private Date testDate;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "result", nullable = false)
    private String result;

}
