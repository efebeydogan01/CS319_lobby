package lobby.pandemica.serviceimpl;

import lobby.pandemica.db.Student;
import lobby.pandemica.dto.StudentDto;
import lobby.pandemica.repository.StudentRepository;
import lobby.pandemica.service.StudentService;
import lobby.pandemica.serviceimpl.base.BaseServiceImpl;
import lobby.pandemica.serviceimpl.mapper.StudentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends BaseServiceImpl<Student, StudentDto> implements StudentService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        super(studentRepository, StudentMapper.INSTANCE);
        this.studentRepository = studentRepository;
    }
}
