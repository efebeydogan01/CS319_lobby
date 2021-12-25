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
import lobby.pandemica.serviceimpl.mapper.StudentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.UUID;


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

    public SeatDto set(RequestSeat requestSeat, UUID userId) throws EntityNotFoundException
    {
        // check if student exists
        // check if section exists
        // check if row and column indexes are valid
        // check if requested seat exists
        // check if seat available
        // find old seat
        // set new seat

        // check if user exists
        Optional<User> infoUser = userRepository.findById(userId);
        if (!infoUser.isPresent())
        {
            LOGGER.warn("The entity to find does not exist!");
            throw new EntityNotFoundException();
        }

        // check if student exists
        Optional<Student> infoStudent = studentRepository.findByUserId(userId);
        if (!infoStudent.isPresent())
        {
            LOGGER.warn("Student cannot be found!");
            throw new EntityNotFoundException();
        }
        Student studentEntity = infoStudent.get();

        // check if section exists
        String courseName = requestSeat.getCourseName();
        Integer sectionNo = requestSeat.getSectionNo();
        Optional<Section> infoSection = sectionRepository.findByCourseNameAndSectionNo(courseName, sectionNo);
        if (!infoSection.isPresent())
        {
            LOGGER.warn("The section of the seat cannot be found!");
            throw new EntityNotFoundException();
        }
        Section sectionEntity = infoSection.get();

        // check if row and column indexes are valid
        Classrooms classrooms = new Classrooms();
        Boolean[][] classroom = classrooms.getClassroom(sectionEntity.getClassroom());
        int maxRow = classroom.length; int maxColumn = classroom[0].length;
        int row = requestSeat.getRowNo(); int column = requestSeat.getColumnNo();
        if (!((row < maxRow && row >= 0 ) && (column < maxColumn && column >= 0) && classroom[row][column] == true))
        {
            LOGGER.warn("Row, column indexes of the seat are not valid!");
            throw new EntityNotFoundException();
        }

        // check if requested seat exists
        Optional<Seat> infoSeat = seatRepository.findBySectionIdAndRowAndColumn(
                sectionEntity.getId(), row, column
        );
        if (!infoSeat.isPresent())
        {
            LOGGER.warn("The section of the seat cannot be found!");
            throw new EntityNotFoundException();
        }
        Seat seatEntity = infoSeat.get();

        // check if seat available
        if (seatEntity.getStudent() != null)
        {
            LOGGER.warn("Requested seat is not available!");
            throw new EntityNotFoundException();
        }

        // find old seat
        Optional<Seat> infoOldSeat = seatRepository.findBySectionIdAndStudentIdAndRowAndColumn(
                sectionEntity.getId(), studentEntity.getId(), row, column
        );
        if (infoOldSeat.isPresent())
        {
            Seat oldSeatEntity = infoOldSeat.get();
            oldSeatEntity.setStudent(null);
        }

        // set new seat
        seatEntity.setStudent(studentEntity);

        return SeatMapper.INSTANCE.entityToDto(seatRepository.save(seatEntity));
    }
}
