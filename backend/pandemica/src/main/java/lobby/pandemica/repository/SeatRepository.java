package lobby.pandemica.repository;

import lobby.pandemica.db.Seat;
import lobby.pandemica.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Repository
public interface SeatRepository extends BaseRepository<Seat, UUID>
{
    List<Seat> findAllBySectionId(UUID sectionID);
    Optional<Seat> findBySectionIdAndRowAndColumn(UUID sectionID, int row, int column);
    Optional<Seat> findBySectionIdAndStudentIdAndRowAndColumn(UUID sectionID, UUID studentId, int row, int column);
}
