package lobby.pandemica.repository;

import lobby.pandemica.db.AcademicPersonnel;
import lobby.pandemica.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Transactional
@Repository
public interface AcademicPersonnelRepository extends BaseRepository<AcademicPersonnel, UUID>
{
}