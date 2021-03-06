package lobby.pandemica.repository;

import lobby.pandemica.db.Section;
import lobby.pandemica.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository class for section class
 */
@Transactional
@Repository
public interface SectionRepository extends BaseRepository<Section, UUID>
{
    Optional<Section> findByCourseNameAndSectionNo(String courseName, Integer sectionNo);
    List<Section> findAllByAcademicPersonnelId(UUID academicPersonnelId);
}
