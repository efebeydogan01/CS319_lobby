package lobby.pandemica.repository;

import lobby.pandemica.db.ViolationReport;
import lobby.pandemica.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Transactional
@Repository
public interface ViolationReportRepository extends BaseRepository<ViolationReport, UUID>
{
	List<ViolationReport> findAllByUserId(UUID userId);
}
