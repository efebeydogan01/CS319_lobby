package lobby.pandemica.repository;

import lobby.pandemica.db.Neighbor;
import lobby.pandemica.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository class for neighbor class
 */
@Transactional
@Repository
public interface NeighborRepository extends BaseRepository<Neighbor, UUID>
{
	Integer countNeighborsByFirstStudentId(UUID firstStudentId);
	List<Neighbor> findAllByFirstStudent_Id(UUID id);
	List<Neighbor> findAllByFirstStudentIdAndSectionId(UUID firstStudentId, UUID sectionId);
	List<Neighbor> findAllBySecondStudentIdAndSectionId(UUID secondStudentId, UUID sectionId);
}
