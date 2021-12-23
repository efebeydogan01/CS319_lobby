package lobby.pandemica.dto;
import lobby.pandemica.dto.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementDto extends BaseDto<UUID>
{
	private UUID id;
	private String title;
	private Date date;
	private String announcementText;
}