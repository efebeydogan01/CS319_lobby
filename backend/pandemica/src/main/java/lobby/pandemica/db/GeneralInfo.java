package lobby.pandemica.db;

import lombok.*;

import java.util.List;

/**
 * Holds the statistics for the university, the case numbers by the user type.
 * This class in not represented in the database, it is just created and sent to frontend when necessary.
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GeneralInfo
{
	private List<Announcement> announcements;
	private int academicCases;
	private int adminCases;
	private int staffCases;
	private int studentCases;
	private double vaccinationRate1;
	private double vaccinationRate2;
}