package lobby.pandemica.service;

import lobby.pandemica.dto.NeighborDto;
import lobby.pandemica.service.base.BaseCrudService;

import java.util.UUID;

public interface NeighborService extends BaseCrudService<NeighborDto>
{
	Boolean getRiskStatus(UUID id);
}
