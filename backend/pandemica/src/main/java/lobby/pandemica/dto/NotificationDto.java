package lobby.pandemica.dto;

import lobby.pandemica.dto.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * Dto of notification entity
 */
public class NotificationDto extends BaseDto<UUID>
{
	private UserDto user;
	private String receivers;
	private String title;
	private String message;
	private Instant createdOn;
	private UserDto receiver;
}
