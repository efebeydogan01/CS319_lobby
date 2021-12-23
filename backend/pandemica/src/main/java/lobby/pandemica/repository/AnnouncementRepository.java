package lobby.pandemica.repository;

import lobby.pandemica.db.Admin;
import lobby.pandemica.db.Announcement;
import lobby.pandemica.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Repository
public interface AnnouncementRepository extends BaseRepository<Announcement, UUID>
{
	Optional<Announcement> findById(UUID id);
}