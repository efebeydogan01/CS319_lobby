package lobby.pandemica.repository;

import lobby.pandemica.db.CovidInformation;
import lobby.pandemica.db.User;
import lobby.pandemica.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
/**
 * Repository class for covid information
 */
@Transactional
@Repository
public interface CovidInformationRepository extends BaseRepository<CovidInformation, UUID>
{
    Optional<CovidInformation> findByUserId(UUID userID);
    Integer countByStatus(String status);
    List<CovidInformation> findAllByStatus(String status);
    CovidInformation getByUserId(UUID userId);
}
