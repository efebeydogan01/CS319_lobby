package lobby.pandemica.dto;

import lobby.pandemica.dto.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

/**
 * Dto of vaccinationInformation entity
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VaccineInformationDto extends BaseDto<UUID>
{
	private UUID id;
	private UserDto user;
	private String vaccineName;
	private Date date;
}
