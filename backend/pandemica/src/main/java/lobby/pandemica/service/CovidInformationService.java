package lobby.pandemica.service;

import lobby.pandemica.dto.CovidInformationDto;
import lobby.pandemica.service.base.BaseCrudService;

import java.util.UUID;

public interface CovidInformationService extends BaseCrudService<CovidInformationDto>
{
    CovidInformationDto get(UUID userId);
}
