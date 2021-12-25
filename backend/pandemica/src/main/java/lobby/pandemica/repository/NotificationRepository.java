package lobby.pandemica.repository;

import lobby.pandemica.db.Notification;
import lobby.pandemica.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface NotificationRepository extends BaseRepository<Notification, UUID>
{
	List<Notification> findAllByReceivers(String receivers);
}
