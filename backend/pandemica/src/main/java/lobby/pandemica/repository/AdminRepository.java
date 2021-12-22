package lobby.pandemica.repository;

import lobby.pandemica.db.Admin;
import lobby.pandemica.db.Student;
import lobby.pandemica.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Transactional
@Repository
public interface AdminRepository extends BaseRepository<Admin, UUID>
{
}