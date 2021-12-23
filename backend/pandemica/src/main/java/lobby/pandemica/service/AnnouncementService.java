package lobby.pandemica.service;

import lobby.pandemica.dto.AcademicPersonnelDto;
import lobby.pandemica.dto.AnnouncementDto;
import lobby.pandemica.service.base.BaseCrudService;

import java.util.UUID;

public interface AnnouncementService extends BaseCrudService<AnnouncementDto>
{
    AnnouncementDto get(UUID id);
}
