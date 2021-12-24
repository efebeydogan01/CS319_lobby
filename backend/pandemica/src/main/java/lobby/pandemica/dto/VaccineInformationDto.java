package lobby.pandemica.dto;

import lobby.pandemica.dto.base.BaseDto;

import java.util.Date;
import java.util.UUID;

public class VaccineInformationDto extends BaseDto<UUID>
{
	private UserDto user;
	private String vaccineName;
	private Date date;
}
