package lobby.pandemica.dto;

import lobby.pandemica.dto.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * Dto of violationReport entity
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViolationReportDto extends BaseDto<UUID>
{
	private UserDto user;
	private String message;
	private String place;
}
