package lobby.pandemica.serviceimpl;

import lobby.pandemica.db.AcademicPersonnel;
import lobby.pandemica.db.Role;
import lobby.pandemica.db.User;
import lobby.pandemica.dto.AcademicPersonnelDto;
import lobby.pandemica.dto.UserDto;
import lobby.pandemica.repository.AcademicPersonnelRepository;
import lobby.pandemica.repository.UserRepository;
import lobby.pandemica.service.AcademicPersonnelService;
import lobby.pandemica.serviceimpl.base.BaseServiceImpl;
import lobby.pandemica.serviceimpl.mapper.AcademicPersonnelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Service
public class AcademicPersonnelServiceImpl extends BaseServiceImpl<AcademicPersonnel, AcademicPersonnelDto> implements AcademicPersonnelService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AcademicPersonnelServiceImpl.class);

    private final UserRepository userRepository;
    private final AcademicPersonnelRepository academicPersonnelRepository;

    public AcademicPersonnelServiceImpl(AcademicPersonnelRepository academicPersonnelRepository, UserRepository userRepository) {
        super(academicPersonnelRepository, AcademicPersonnelMapper.INSTANCE);
        this.academicPersonnelRepository = academicPersonnelRepository;
        this.userRepository = userRepository;
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