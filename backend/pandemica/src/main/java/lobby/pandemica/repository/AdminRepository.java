package lobby.pandemica.repository;

import lobby.pandemica.db.Admin;
import lobby.pandemica.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository class for admin
 */
@Transactional
@Repository
public interface AdminRepository extends BaseRepository<Admin, UUID>
{
	Optional<Admin> findByUserId(UUID userId);
}
