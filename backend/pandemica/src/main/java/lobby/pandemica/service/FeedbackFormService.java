package lobby.pandemica.service;

import lobby.pandemica.dto.FeedbackFormDto;
import lobby.pandemica.service.base.BaseCrudService;

import java.util.List;
import java.util.UUID;

public interface FeedbackFormService extends BaseCrudService<FeedbackFormDto>
{
	List<FeedbackFormDto> readAllFromUser(UUID id);
}
