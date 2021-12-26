package lobby.pandemica.serviceimpl;

import lobby.pandemica.db.*;
import lobby.pandemica.dto.AcademicPersonnelDto;
import lobby.pandemica.dto.AdminDto;
import lobby.pandemica.dto.SeatDto;
import lobby.pandemica.dto.SectionDto;
import lobby.pandemica.repository.AdminRepository;
import lobby.pandemica.repository.SectionRepository;
import lobby.pandemica.repository.UserRepository;
import lobby.pandemica.service.AdminService;
import lobby.pandemica.service.SeatService;
import lobby.pandemica.serviceimpl.base.BaseServiceImpl;
import lobby.pandemica.serviceimpl.mapper.AdminMapper;
import lobby.pandemica.serviceimpl.mapper.SectionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin, AdminDto> implements AdminService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final SectionRepository sectionRepository;
    private final SeatService seatService;

    public AdminServiceImpl(AdminRepository adminRepository, UserRepository userRepository,
                            SectionRepository sectionRepository, SeatService seatService) {
        super(adminRepository, AdminMapper.INSTANCE);
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
        this.sectionRepository = sectionRepository;
        this.seatService = seatService;
    }

    @Override
    public AdminDto create(AdminDto dto) throws EntityNotFoundException
    {
        Admin entity = AdminMapper.INSTANCE.dtoToEntity(dto);
        if (entity == null) {
            LOGGER.warn("The entity to save cannot be empty!");
            throw new EntityNotFoundException();
        }
        Optional<User> infoUser = userRepository.findByBilkentId(dto.getUser().getBilkentId());
        if (!infoUser.isPresent())
        {
            LOGGER.warn("The user of the admin cannot be empty!");
            throw new EntityNotFoundException();
        }
        entity.setUser(infoUser.get());
        return super.create(AdminMapper.INSTANCE.entityToDto(entity));
    }

    @Override
    public AdminDto getUserWithRole(UUID userId)
    {
        Optional<Admin> optionalAdmin = adminRepository.findByUserId(userId);
        if (!optionalAdmin.isPresent())
        {
            LOGGER.warn("The entity to find does not exist!");
            throw new EntityNotFoundException();
        }
        return AdminMapper.INSTANCE.entityToDto(optionalAdmin.get());
    }

    public void initializeSeatingPlan(SectionDto sectionDto)
    {
        String courseName = sectionDto.getCourseName();
        Integer sectionNo = sectionDto.getSectionNo();
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

        Classrooms classrooms = new Classrooms();
        Boolean[][] classroom = classrooms.getClassroom(sectionDto.getClassroom());
        int rowLength = classroom.length; int columnLength = classroom[0].length;
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < columnLength; j++) {
                SeatDto seatDto = new SeatDto(UUID.randomUUID(), sectionDto, classroom[i][j], i, j, null);
                seatService.create(seatDto);
            }
        }
    }
}
