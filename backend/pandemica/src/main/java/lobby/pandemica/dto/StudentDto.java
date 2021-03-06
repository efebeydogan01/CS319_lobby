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
 * Dto of student entity
 */
public class StudentDto extends BaseDto<UUID>
{
    private UUID id;
    private UserDto user;
    private String department;
    private String year;
}