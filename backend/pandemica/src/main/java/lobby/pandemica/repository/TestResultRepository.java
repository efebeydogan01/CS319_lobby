package lobby.pandemica.repository;
import lobby.pandemica.db.TestResult;
import lobby.pandemica.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * Repository for test result class
 */
@Transactional
@Repository
public interface TestResultRepository extends BaseRepository<TestResult, UUID>
{
	List<TestResult> findAllByUserId(UUID userId);
}
