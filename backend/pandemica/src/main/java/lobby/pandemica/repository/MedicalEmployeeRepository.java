package lobby.pandemica.repository;

import lobby.pandemica.db.MedicalEmployee;
import lobby.pandemica.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Repository
public interface MedicalEmployeeRepository extends BaseRepository<MedicalEmployee, UUID>
{
	Optional<MedicalEmployee> findByUserId(UUID userId);
}
