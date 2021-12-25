package lobby.pandemica.db;

import lobby.pandemica.db.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Represents a covid information of a specific user. The user of the covid information is held as a reference in this class.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "covid_information")
@Entity
public class CovidInformation extends BaseEntity {
    //Attributes
    @Column(name="status")
    private String status;

    @Column(name = "hes_code")
    private String hesCode;

    @Column(name = "allowed_on_campus")
    private Boolean allowedOnCampus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
