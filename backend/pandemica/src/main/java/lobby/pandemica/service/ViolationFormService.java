package lobby.pandemica.service;

import lobby.pandemica.dto.ViolationReportDto;
import lobby.pandemica.service.base.BaseCrudService;

import java.util.List;
import java.util.UUID;

public interface ViolationFormService extends BaseCrudService<ViolationReportDto>
{
	List<ViolationReportDto> readAllFromUser(UUID id);
}
