package lobby.pandemica.service;

import lobby.pandemica.dto.AcademicPersonnelDto;
import lobby.pandemica.service.base.BaseCrudService;

import java.util.UUID;

public interface AcademicPersonnelService extends BaseCrudService<AcademicPersonnelDto>
{
	AcademicPersonnelDto getUserWithRole(UUID userId);
}
