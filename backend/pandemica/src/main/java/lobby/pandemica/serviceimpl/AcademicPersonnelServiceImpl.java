package lobby.pandemica.serviceimpl;

import lobby.pandemica.db.*;
import lobby.pandemica.dto.AcademicPersonnelDto;
import lobby.pandemica.dto.UserDto;
import lobby.pandemica.repository.AcademicPersonnelRepository;
import lobby.pandemica.repository.SeatRepository;
import lobby.pandemica.repository.SectionRepository;
import lobby.pandemica.repository.UserRepository;
import lobby.pandemica.service.AcademicPersonnelService;
import lobby.pandemica.serviceimpl.base.BaseServiceImpl;
import lobby.pandemica.serviceimpl.mapper.AcademicPersonnelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
public class AcademicPersonnelServiceImpl extends BaseServiceImpl<AcademicPersonnel, AcademicPersonnelDto> implements AcademicPersonnelService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AcademicPersonnelServiceImpl.class);

    private final UserRepository userRepository;
    private final AcademicPersonnelRepository academicPersonnelRepository;
    private final SectionRepository sectionRepository;
    private final SeatRepository seatRepository;

    public AcademicPersonnelServiceImpl(AcademicPersonnelRepository academicPersonnelRepository, UserRepository userRepository,
                                        SectionRepository sectionRepository, SeatRepository seatRepository) {
        super(academicPersonnelRepository, AcademicPersonnelMapper.INSTANCE);
        this.academicPersonnelRepository = academicPersonnelRepository;
        this.userRepository = userRepository;
        this.sectionRepository = sectionRepository;
        this.seatRepository = seatRepository;
    }

    public List<SectionWithSeats> getSectionsWithSeats(UUID userId) throws EntityNotFoundException
    {
        Optional<User> infoUser = userRepository.findById(userId);
        if (!infoUser.isPresent())
        {
            LOGGER.warn("The entity to find does not exist!");
            throw new EntityNotFoundException();
        }

        Optional<AcademicPersonnel> infoAcademicPersonnel = academicPersonnelRepository.findByUserId(userId);
        if (!infoAcademicPersonnel.isPresent())
        {
            LOGGER.warn("The entity to find does not exist!");
            throw new EntityNotFoundException();
        }
        AcademicPersonnel academicPersonnelEntity = infoAcademicPersonnel.get();

        List<SectionWithSeats> sectionWithSeatsList = new ArrayList<>();

        List<Section> academicPersonnelSections = sectionRepository.findAllByAcademicPersonnelId(academicPersonnelEntity.getId());
        for (int i = 0; i < academicPersonnelSections.size(); i++) {
            Section section = academicPersonnelSections.get(i);
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

    @Override
    public AcademicPersonnelDto create(AcademicPersonnelDto dto) throws EntityNotFoundException
    {

        AcademicPersonnel entity = AcademicPersonnelMapper.INSTANCE.dtoToEntity(dto);
        if (entity == null) {
            LOGGER.warn("The entity to save cannot be empty!");
            throw new EntityNotFoundException();
        }
        UserDto userDto = dto.getUser();
        Optional<User> infoUser = userRepository.findByBilkentId(userDto.getBilkentId());
        if (!infoUser.isPresent())
        {
            LOGGER.warn("The user of the section cannot be empty!");
            throw new EntityNotFoundException();
        }
        entity.setUser(infoUser.get());
        if (!userDto.getRole().equals(Role.ROLES.ACADEMIC_PERSONNEL.name()))
        {
            LOGGER.warn("The user of the academic personnel must be an academic personnel!");
            throw new EntityNotFoundException();
        }
        return super.create(AcademicPersonnelMapper.INSTANCE.entityToDto(entity));
    }

    @Override
    public AcademicPersonnelDto getUserWithRole(UUID userId)
    {
        Optional<AcademicPersonnel> optionalAcademicPersonnel = academicPersonnelRepository.findByUserId(userId);
        if (!optionalAcademicPersonnel.isPresent())
        {
            LOGGER.warn("The entity to find does not exist!");
            throw new EntityNotFoundException();
        }
        return AcademicPersonnelMapper.INSTANCE.entityToDto(optionalAcademicPersonnel.get());
    }


}