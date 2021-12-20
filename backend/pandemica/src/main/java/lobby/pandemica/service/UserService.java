package lobby.pandemica.service;

import lobby.pandemica.dto.UserDto;
import lobby.pandemica.service.base.BaseCrudService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends BaseCrudService<UserDto>
{
}
