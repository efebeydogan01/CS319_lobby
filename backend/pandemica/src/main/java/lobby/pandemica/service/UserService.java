package lobby.pandemica.service;

import lobby.pandemica.db.TempDetails;
import lobby.pandemica.dto.UserDto;
import lobby.pandemica.service.base.BaseCrudService;

import java.util.UUID;

public interface UserService extends BaseCrudService<UserDto>
{
    UserDto get(TempDetails details);
}
