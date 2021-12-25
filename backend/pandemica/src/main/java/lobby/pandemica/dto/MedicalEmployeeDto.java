package lobby.pandemica.dto;

import lobby.pandemica.db.User;
import lobby.pandemica.dto.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * Dto of medical employee entity
 */
public class MedicalEmployeeDto extends BaseDto<UUID>
{
    private UUID id;
    private UserDto user;
    private String type;
    private String workingLocation;

}