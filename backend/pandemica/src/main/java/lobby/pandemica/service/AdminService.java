package lobby.pandemica.service;

import lobby.pandemica.dto.AdminDto;
import lobby.pandemica.service.base.BaseCrudService;

import java.util.UUID;

public interface AdminService extends BaseCrudService<AdminDto>
{
	AdminDto getUserWithRole(UUID userId);
}
