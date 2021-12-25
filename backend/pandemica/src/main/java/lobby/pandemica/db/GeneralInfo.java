package lobby.pandemica.db;

import lombok.*;

import java.util.List;

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