package lobby.pandemica.repository;

import lobby.pandemica.db.VaccineInformation;
import lobby.pandemica.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Transactional
@Repository
public interface VaccineInformationRepository extends BaseRepository<VaccineInformation, UUID>
{
}
