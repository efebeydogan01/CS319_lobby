package lobby.pandemica.repository;

import lobby.pandemica.db.FeedbackForm;
import lobby.pandemica.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * Repository class for feedback form class
 */
@Repository
@Transactional
public interface FeedbackFormRepository extends BaseRepository<FeedbackForm, UUID>
{
	List<FeedbackForm> findAllByUserId(UUID userId);
}
