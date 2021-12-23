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
	private Integer academicCases;
	private Integer adminCases;
	private Integer staffCases;
	private Integer studentCases;
	private Integer vaccinationRate;
}
