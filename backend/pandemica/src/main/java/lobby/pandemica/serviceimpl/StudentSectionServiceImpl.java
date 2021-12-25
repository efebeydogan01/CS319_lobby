package lobby.pandemica.serviceimpl;

import lobby.pandemica.db.*;
import lobby.pandemica.dto.NeighborDto;
import lobby.pandemica.dto.SectionDto;
import lobby.pandemica.dto.StudentDto;
import lobby.pandemica.dto.StudentSectionDto;
import lobby.pandemica.repository.*;
import lobby.pandemica.service.NeighborService;
import lobby.pandemica.service.StudentSectionService;
import lobby.pandemica.serviceimpl.base.BaseServiceImpl;
import lobby.pandemica.serviceimpl.mapper.NeighborMapper;
import lobby.pandemica.serviceimpl.mapper.SeatMapper;
import lobby.pandemica.serviceimpl.mapper.StudentSectionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class StudentSectionServiceImpl extends BaseServiceImpl<StudentSection, StudentSectionDto> implements StudentSectionService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentSectionServiceImpl.class);

    private final StudentSectionRepository studentSectionRepository;
    private final StudentRepository studentRepository;
    private final SectionRepository sectionRepository;
    private final UserRepository userRepository;

    public StudentSectionServiceImpl(StudentSectionRepository studentSectionRepository, StudentRepository studentRepository,
                                     SectionRepository sectionRepository, UserRepository userRepository) {
        super(studentSectionRepository, StudentSectionMapper.INSTANCE);
        this.studentSectionRepository = studentSectionRepository;
        this.studentRepository = studentRepository;
        this.sectionRepository = sectionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public StudentSectionDto create(StudentSectionDto dto) throws EntityNotFoundException
    {
        StudentSection entity = StudentSectionMapper.INSTANCE.dtoToEntity(dto);
        if (entity == null) {
            LOGGER.warn("The entity to save cannot be empty!");
            throw new EntityNotFoundException();
        }

        SectionDto sectionDto = dto.getSection();
        String courseName = sectionDto.getCourseName();
        Integer sectionNo = sectionDto.getSectionNo();
        Optional<Section> infoSection = sectionRepository.findByCourseNameAndSectionNo(courseName, sectionNo);
        if (!infoSection.isPresent())
        {
            LOGGER.warn("The section of the student section relation cannot be found!");
            throw new EntityNotFoundException();
        }
        entity.setSection(infoSection.get());

        StudentDto studentDto = dto.getStudent();
        Integer bilkentID = studentDto.getUser().getBilkentId();
        Optional<User> infoUser = userRepository.findByBilkentId(bilkentID);
        Optional<Student> infoStudent = studentRepository.findByUserId(infoUser.get().getId());
        if (!infoStudent.isPresent())
        {
            LOGGER.warn("The student of the seat cannot be found!");
            throw new EntityNotFoundException();
        }
        entity.setStudent(infoStudent.get());

        return StudentSectionMapper.INSTANCE.entityToDto(studentSectionRepository.save(entity));
    }
}
