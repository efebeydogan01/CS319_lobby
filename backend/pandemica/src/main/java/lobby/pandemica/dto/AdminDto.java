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
 * Dto of admin entity
 */
public class AdminDto extends BaseDto<UUID>
{
    private UUID id;
    private UserDto user;
}