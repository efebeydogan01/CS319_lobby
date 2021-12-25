package lobby.pandemica.serviceimpl;

import lobby.pandemica.db.Notification;
import lobby.pandemica.dto.NotificationDto;
import lobby.pandemica.repository.NotificationRepository;
import lobby.pandemica.repository.UserRepository;
import lobby.pandemica.repository.base.BaseRepository;
import lobby.pandemica.service.NotificationService;
import lobby.pandemica.serviceimpl.base.BaseServiceImpl;
import lobby.pandemica.serviceimpl.mapper.NotificationMapper;
import lobby.pandemica.serviceimpl.mapper.base.BaseMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NotificationServiceImpl extends BaseServiceImpl<Notification, NotificationDto> implements NotificationService
{
	private NotificationRepository notificationRepository;
	private UserRepository userRepository;

	public NotificationServiceImpl(BaseRepository<Notification, UUID> baseRepository,
								   BaseMapper<Notification, NotificationDto> baseMapper,
								   NotificationRepository notificationRepository, UserRepository userRepository)
	{
		super(baseRepository, baseMapper);
		this.notificationRepository = notificationRepository;
		this.userRepository = userRepository;
	}

	@Override
	public List<NotificationDto> getNotificationsForUser(UUID id)
	{
		String userRole = userRepository.getById(id).getRole();
		List<Notification> notificationsForAllUsers = notificationRepository.findAllByReceivers("ALL");
		List<Notification> notificationsForRole = notificationRepository.findAllByReceivers(userRole);
		List<Notification> notificationsForUser = notificationRepository.findAllByReceiverId(id);
		notificationsForRole.addAll(notificationsForAllUsers);
		notificationsForRole.addAll(notificationsForUser);
		return NotificationMapper.INSTANCE.entityListToDtoList(notificationsForRole);
	}
}
