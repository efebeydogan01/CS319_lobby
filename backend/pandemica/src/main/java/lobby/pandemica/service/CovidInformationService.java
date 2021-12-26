package lobby.pandemica.service;

import lobby.pandemica.db.ExtendedCovidInformation;
import lobby.pandemica.dto.CovidInformationDto;
import lobby.pandemica.service.base.BaseCrudService;

import java.util.List;
import java.util.UUID;

public interface CovidInformationService extends BaseCrudService<CovidInformationDto>
{
    CovidInformationDto get(UUID userId);
    List<ExtendedCovidInformation> getExtendedCovidInformation();
}
