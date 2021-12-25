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
/**
 * Dto of announcement entity
 */
public class AnnouncementDto extends BaseDto<UUID>
{
	private UUID id;
	private String title;
	private String date;
	private String announcementText;
}