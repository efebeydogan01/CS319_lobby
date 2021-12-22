package lobby.pandemica.repository;

import lobby.pandemica.db.Neighbor;
import lobby.pandemica.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Transactional
@Repository
public interface NeighborRepository extends BaseRepository<Neighbor, UUID>
{
	Integer countNeighborsByFirstStudentId(UUID firstStudentId);
	List<Neighbor> findAllByFirstStudent_Id(UUID id);
}
