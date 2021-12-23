package lobby.pandemica.db;

import lobby.pandemica.db.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "announcements")

public class Announcement extends BaseEntity {
    //Attributes
    @Column(name = "title")
    private String title;

    @Column(name = "date")
    private Date date;

    @Column(name = "announcement_text")
    private String announcementText;
    /*
    @Column(name = "academic_staff_cases")
    private int academicStaffCases;

    @Column(name = "administrative_staff_cases")
    private int administrativeStaffCases;

    @Column(name = "support_staff_cases")
    private int supportStaffCases;

    @Column(name = "student_cases")
    private int studentCases;

    @Column(name = "vaccination_rate")
    private double vaccinationRate;
     */
}
