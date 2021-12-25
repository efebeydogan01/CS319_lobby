package lobby.pandemica.serviceimpl;

import lobby.pandemica.db.*;
import lobby.pandemica.dto.StudentDto;
import lobby.pandemica.repository.SeatRepository;
import lobby.pandemica.repository.StudentRepository;
import lobby.pandemica.repository.StudentSectionRepository;
import lobby.pandemica.repository.UserRepository;
import lobby.pandemica.service.StudentService;
import lobby.pandemica.serviceimpl.base.BaseServiceImpl;
import lobby.pandemica.serviceimpl.mapper.StudentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
public class StudentServiceImpl extends BaseServiceImpl<Student, StudentDto> implements StudentService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final StudentSectionRepository studentSectionRepository;
    private final SeatRepository seatRepository;

    public StudentServiceImpl(StudentRepository studentRepository, UserRepository userRepository,
                              StudentSectionRepository studentSectionRepository, SeatRepository seatRepository) {
        super(studentRepository, StudentMapper.INSTANCE);
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.studentSectionRepository = studentSectionRepository;
        this.seatRepository = seatRepository;
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

    public List<SectionWithSeats> getSectionsWithSeats(UUID studentId) throws EntityNotFoundException
    {
        Optional<Student> infoStudent = studentRepository.findById(studentId);
        if (!infoStudent.isPresent())
        {
            LOGGER.warn("The entity to find does not exist!");
            throw new EntityNotFoundException();
        }
        Student studentEntity = infoStudent.get();

        List<StudentSection> studentSections = studentSectionRepository.findAllByStudentId(studentEntity.getId());
        if (studentSections == null) {
            LOGGER.warn("The entity to find does not exist!");
            throw new EntityNotFoundException();
        }

        List<SectionWithSeats> sectionWithSeatsList = new ArrayList<>();
        for (int i = 0; i < studentSections.size(); i++) {
            StudentSection studentSection = studentSections.get(i);
            Section section = studentSection.getSection();
            List<Seat> seats = seatRepository.findAllBySectionId(section.getId());
            order(seats);

            SectionWithSeats sectionWithSeats = new SectionWithSeats();
            sectionWithSeats.setSection(section);
            sectionWithSeats.setSeats(seats);
            sectionWithSeatsList.add(sectionWithSeats);
        }

        if (sectionWithSeatsList == null) {
            LOGGER.warn("Getting entity was unsuccessful due to an error with the entities given.");
            throw new EntityNotFoundException();
        }

        return sectionWithSeatsList;
    }

    private void order(List<Seat> seats) {

        Collections.sort(seats, new Comparator() {

            public int compare(Object o1, Object o2) {

                Integer row1 = ((Seat) o1).getRow();
                Integer row2 = ((Seat) o2).getRow();
                int comp = row1.compareTo(row2);

                if (comp != 0) {
                    return comp;
                }

                Integer column1 = ((Seat) o1).getColumn();
                Integer column2 = ((Seat) o2).getColumn();
                return column1.compareTo(column2);
            }});
    }

}
