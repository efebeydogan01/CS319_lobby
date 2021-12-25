package lobby.pandemica.service;

import lobby.pandemica.dto.NotificationDto;
import lobby.pandemica.service.base.BaseCrudService;

import java.util.List;
import java.util.UUID;

public interface NotificationService extends BaseCrudService<NotificationDto>
{
	List<NotificationDto> getNotificationsForUser(UUID id);
}
