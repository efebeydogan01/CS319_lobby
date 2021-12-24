package lobby.pandemica.service;

import lobby.pandemica.dto.RequestFormDto;
import lobby.pandemica.service.base.BaseCrudService;

import java.util.List;
import java.util.UUID;

public interface RequestFormService extends BaseCrudService<RequestFormDto>
{
	List<RequestFormDto> readAllFromUser(UUID id);
}
