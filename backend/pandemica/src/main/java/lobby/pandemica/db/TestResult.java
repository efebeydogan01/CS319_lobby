package lobby.pandemica.db;

import lobby.pandemica.db.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "test_results")

public class TestResult extends BaseEntity {
    //Attributes
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private CovidInformation covidInformation;

    @Column(name = "test_date", nullable = false)
    private Date testDate;

    @Column(name = "test_place", nullable = false)
    private String testPlace;

    @Column(name = "result", nullable = false)
    private Boolean result;

}
