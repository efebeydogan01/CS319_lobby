package lobby.pandemica.repository;

import lobby.pandemica.db.RequestForm;
import lobby.pandemica.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * Repository class for request form class
 */
@Transactional
@Repository
public interface RequestFormRepository extends BaseRepository<RequestForm, UUID>
{
	List<RequestForm> findAllByUserId(UUID userId);
}
