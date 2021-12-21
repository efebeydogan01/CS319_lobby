package lobby.pandemica.service;

import lobby.pandemica.db.TempDetails;
import lobby.pandemica.dto.CovidInformationDto;
import lobby.pandemica.dto.UserDto;
import lobby.pandemica.service.base.BaseCrudService;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface CovidInformationService extends BaseCrudService<CovidInformationDto>
{
    CovidInformationDto get(UUID userId);
}
