package lobby.pandemica.serviceimpl;

import lobby.pandemica.db.*;
import lobby.pandemica.dto.AcademicPersonnelDto;
import lobby.pandemica.dto.SeatDto;
import lobby.pandemica.dto.SectionDto;
import lobby.pandemica.dto.StudentDto;
import lobby.pandemica.repository.SeatRepository;
import lobby.pandemica.repository.SectionRepository;
import lobby.pandemica.repository.StudentRepository;
import lobby.pandemica.repository.UserRepository;
import lobby.pandemica.service.SeatService;
import lobby.pandemica.serviceimpl.base.BaseServiceImpl;
import lobby.pandemica.serviceimpl.mapper.SeatMapper;
import lobby.pandemica.serviceimpl.mapper.SectionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;


@Service
public class SeatServiceImpl extends BaseServiceImpl<Seat, SeatDto> implements SeatService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SeatServiceImpl.class);

    private final SeatRepository seatRepository;
    private final SeatMapper seatMapper;
    private final SectionRepository sectionRepository;
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;

    public SeatServiceImpl(SeatRepository seatRepository, SeatMapper seatMapper,
                           SectionRepository sectionRepository, UserRepository userRepository,
                           StudentRepository studentRepository) {
        super(seatRepository, SeatMapper.INSTANCE);
        this.seatRepository = seatRepository;
        this.seatMapper = seatMapper;
        this.sectionRepository = sectionRepository;
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public SeatDto create(SeatDto dto) throws EntityNotFoundException
    {
        Seat entity = SeatMapper.INSTANCE.dtoToEntity(dto);
        if (entity == null)
        {
            LOGGER.warn("The entity to save cannot be empty!");
            throw new EntityNotFoundException();
        }

        SectionDto sectionDto = dto.getSection();
        String courseName = sectionDto.getCourseName();
        Integer sectionNo = sectionDto.getSectionNo();
        Optional<Section> infoSection = sectionRepository.findByCourseNameAndSectionNo(courseName, sectionNo);
        if (!infoSection.isPresent())
        {
            LOGGER.warn("The section of the seat cannot be found!");
            throw new EntityNotFoundException();
        }
        entity.setSection(infoSection.get());

        StudentDto studentDto = dto.getStudent();
        if (studentDto != null)
        {
            Integer bilkentID = studentDto.getUser().getBilkentId();
            Optional<User> infoUser = userRepository.findByBilkentId(bilkentID);
            Optional<Student> infoStudent = studentRepository.findByUserId(infoUser.get().getId());
            if (!infoStudent.isPresent())
            {
                LOGGER.warn("The student of the seat cannot be found!");
                throw new EntityNotFoundException();
            }
            entity.setStudent(infoStudent.get());
        }

        return SeatMapper.INSTANCE.entityToDto(seatRepository.save(entity));
    }
}
