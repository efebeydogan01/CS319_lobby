package lobby.pandemica.serviceimpl;

import lobby.pandemica.db.Student;
import lobby.pandemica.db.User;
import lobby.pandemica.dto.StudentDto;
import lobby.pandemica.repository.StudentRepository;
import lobby.pandemica.repository.UserRepository;
import lobby.pandemica.service.StudentService;
import lobby.pandemica.serviceimpl.base.BaseServiceImpl;
import lobby.pandemica.serviceimpl.mapper.StudentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentServiceImpl extends BaseServiceImpl<Student, StudentDto> implements StudentService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    public StudentServiceImpl(StudentRepository studentRepository,UserRepository userRepository) {
        super(studentRepository, StudentMapper.INSTANCE);
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public StudentDto getUserWithRole(UUID userId)
    {
        Optional<Student> optionalStudent = studentRepository.findByUserId(userId);
        if (!optionalStudent.isPresent())
        {
            LOGGER.warn("The entity to find does not exist!");
            throw new EntityNotFoundException();
        }
        return StudentMapper.INSTANCE.entityToDto(optionalStudent.get());
    }

    @Override
    public StudentDto create(StudentDto dto) throws EntityNotFoundException
    {
        Student entity = StudentMapper.INSTANCE.dtoToEntity(dto);
        if (entity == null) {
            LOGGER.warn("The entity to save cannot be empty!");
            throw new EntityNotFoundException();
        }
        Optional<User> infoUser = userRepository.findByBilkentId(dto.getUser().getBilkentId());
        if (!infoUser.isPresent())
        {
            LOGGER.warn("The user of the student cannot be empty!");
            throw new EntityNotFoundException();
        }
        entity.setUser(infoUser.get());
        return super.create(StudentMapper.INSTANCE.entityToDto(entity));
    }
}
