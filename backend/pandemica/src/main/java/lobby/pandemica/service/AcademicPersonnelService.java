package lobby.pandemica.service;

import lobby.pandemica.db.SectionWithSeats;
import lobby.pandemica.dto.AcademicPersonnelDto;
import lobby.pandemica.service.base.BaseCrudService;

import java.util.List;
import java.util.UUID;

public interface AcademicPersonnelService extends BaseCrudService<AcademicPersonnelDto>
{
	AcademicPersonnelDto getUserWithRole(UUID userId);
	List<SectionWithSeats> getSectionsWithSeats(UUID userId);
}
