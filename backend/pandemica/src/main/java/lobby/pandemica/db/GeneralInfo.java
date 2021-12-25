package lobby.pandemica.db;

import lombok.*;

import java.util.List;
/**
 * Entity class representing general covid related info of the university, involving numbers of cases by user roles, announcements
 * made by admins etc.
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