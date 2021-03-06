package lobby.pandemica.repository;

import lobby.pandemica.db.Section;
import lobby.pandemica.db.StudentSection;
import lobby.pandemica.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * Repository for student-section class
 */
@Transactional
@Repository
public interface StudentSectionRepository extends BaseRepository<StudentSection, UUID>
{
    List<StudentSection> findAllByStudentId(UUID studentId);
}
