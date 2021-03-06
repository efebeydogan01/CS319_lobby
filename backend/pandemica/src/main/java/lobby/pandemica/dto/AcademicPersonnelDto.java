package lobby.pandemica.dto;
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
 * Dto of academicPersonnel entity
 */
public class AcademicPersonnelDto extends BaseDto<UUID>
{
    private UUID id;
    private String department;
    private UserDto user;
}
