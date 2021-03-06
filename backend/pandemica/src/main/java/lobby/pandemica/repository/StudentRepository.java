package lobby.pandemica.repository;

import lobby.pandemica.db.Student;
import lobby.pandemica.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository class for student class
 */
@Transactional
@Repository
public interface StudentRepository extends BaseRepository<Student, UUID>
{
	Optional<Student> findByUserId(UUID userId);
}
