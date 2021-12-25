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
	private Integer academicCases;
	private Integer adminCases;
	private Integer staffCases;
	private Integer studentCases;
	private Integer vaccinationRate;
}