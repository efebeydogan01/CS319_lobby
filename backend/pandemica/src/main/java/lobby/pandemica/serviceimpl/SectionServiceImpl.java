package lobby.pandemica.serviceimpl;

import com.sun.xml.bind.v2.model.core.ID;
import lobby.pandemica.db.*;
import lobby.pandemica.dto.AcademicPersonnelDto;
import lobby.pandemica.dto.SeatDto;
import lobby.pandemica.dto.SectionDto;
import lobby.pandemica.dto.UserDto;
import lobby.pandemica.repository.AcademicPersonnelRepository;
import lobby.pandemica.repository.SeatRepository;
import lobby.pandemica.repository.SectionRepository;
import lobby.pandemica.repository.UserRepository;
import lobby.pandemica.service.AdminService;
import lobby.pandemica.service.SectionService;
import lobby.pandemica.serviceimpl.base.BaseServiceImpl;
import lobby.pandemica.serviceimpl.mapper.AcademicPersonnelMapper;
import lobby.pandemica.serviceimpl.mapper.SeatMapper;
import lobby.pandemica.serviceimpl.mapper.SectionMapper;
import lobby.pandemica.serviceimpl.mapper.StudentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

/**
 * Implementation of SectionService interface
 */
@Service
public class SectionServiceImpl extends BaseServiceImpl<Section, SectionDto> implements SectionService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SectionServiceImpl.class);

    private final SectionRepository sectionRepository;
    private final UserRepository userRepository;
    private final SectionMapper sectionMapper;
    private final AcademicPersonnelRepository academicPersonnelRepository;
    private final SeatRepository seatRepository;
    private final SeatMapper seatMapper;
    private final AdminService adminService;

    /**
     * All args constructor
     * @param sectionRepository
     * @param sectionMapper
     * @param userRepository
     * @param academicPersonnelRepository
     * @param seatRepository
     * @param seatMapper
     * @param adminService
     */
    public SectionServiceImpl(SectionRepository sectionRepository, SectionMapper sectionMapper,
                              UserRepository userRepository, AcademicPersonnelRepository academicPersonnelRepository,
                              SeatRepository seatRepository, SeatMapper seatMapper, AdminService adminService) {
        super(sectionRepository, SectionMapper.INSTANCE);
        this.sectionRepository = sectionRepository;
        this.userRepository = userRepository;
        this.sectionMapper = sectionMapper;
        this.academicPersonnelRepository = academicPersonnelRepository;
        this.seatRepository = seatRepository;
        this.seatMapper = seatMapper;
        this.adminService = adminService;
    }

    /**
     *
     * @param requestSeatingPlan An object consisting of a courseName (String) and a sectionNo (Integer)
     *                           of the seating plan to be fetched
     * @return A List consisting of SeatDto's, which represents all the seats in the section
     */
    public List<SeatDto> getSeatingPlan(RequestSeatingPlan requestSeatingPlan)
    {
        String courseName = requestSeatingPlan.getCourseName();
        Integer sectionNo = requestSeatingPlan.getSectionNo();
        if (courseName == null || sectionNo == null)
        {
            LOGGER.warn("Seating plan information cannot be empty!");
            throw new EntityNotFoundException();
        }
        Optional<Section> infoSection = sectionRepository.findByCourseNameAndSectionNo(courseName, sectionNo);
        if (!infoSection.isPresent())
        {
            LOGGER.warn("The section cannot be found!");
            throw new EntityNotFoundException();
        }
        SectionDto sectionDto = sectionMapper.entityToDto(infoSection.get());
        List<Seat> infoSeats = seatRepository.findAllBySectionId(sectionDto.getId());
        order(infoSeats);
        List<SeatDto> seatDtos = new ArrayList<>();
        for (int i = 0; i < infoSeats.size(); i++) {
            seatDtos.add(seatMapper.entityToDto(infoSeats.get(i)));
        }
        return seatDtos;
    }

  /**
    * Puts the sectionDto object to the database
    * @param dto sectionDto object to be added to the database
    * @return sectionDto object that is added to the database
    * @throws EntityNotFoundException
   */
    public List<Section> getSections(UUID userId)
    {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            LOGGER.warn("The user cannot be found!");
            throw new EntityNotFoundException();
        }

        Optional<AcademicPersonnel> academicPersonnelOptional = academicPersonnelRepository.findByUserId(userId);
        if (!academicPersonnelOptional.isPresent()) {
            LOGGER.warn("The academic personnel entity cannot be found!");
            throw new EntityNotFoundException();
        }

        List<Section> sections = sectionRepository.findAllByAcademicPersonnelId(academicPersonnelOptional.get().getId());
        return sections;
    }

    @Override
    public SectionDto create(SectionDto dto) throws EntityNotFoundException
    {
        Section entity = SectionMapper.INSTANCE.dtoToEntity(dto);
        if (entity == null) {
            LOGGER.warn("The entity to save cannot be empty!");
            throw new EntityNotFoundException();
        }
        AcademicPersonnelDto academicPersonnelDto = dto.getAcademicPersonnel();
        Integer bilkentID = academicPersonnelDto.getUser().getBilkentId();
        Optional<User> infoUser = userRepository.findByBilkentId(bilkentID);
        Optional<AcademicPersonnel> infoAcademic = academicPersonnelRepository.findByUserId(infoUser.get().getId());
        if (!infoAcademic.isPresent())
        {
            LOGGER.warn("The academic personnel of the section cannot be empty!");
            throw new EntityNotFoundException();
        }
        entity.setAcademicPersonnel(infoAcademic.get());
        Section finalEntity = sectionRepository.save(entity);
        adminService.initializeSeatingPlan(dto);
        return SectionMapper.INSTANCE.entityToDto(finalEntity);
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
