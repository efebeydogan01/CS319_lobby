package lobby.pandemica.serviceimpl;

import lobby.pandemica.db.CovidInformation;
import lobby.pandemica.db.Role;
import lobby.pandemica.db.Section;
import lobby.pandemica.db.User;
import lobby.pandemica.dto.CovidInformationDto;
import lobby.pandemica.dto.SectionDto;
import lobby.pandemica.dto.UserDto;
import lobby.pandemica.repository.SectionRepository;
import lobby.pandemica.repository.UserRepository;
import lobby.pandemica.service.SectionService;
import lobby.pandemica.serviceimpl.base.BaseServiceImpl;
import lobby.pandemica.serviceimpl.mapper.CovidInformationMapper;
import lobby.pandemica.serviceimpl.mapper.SectionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class SectionServiceImpl extends BaseServiceImpl<Section, SectionDto> implements SectionService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SectionServiceImpl.class);

    private final SectionRepository sectionRepository;
    private final UserRepository userRepository;

    public SectionServiceImpl(SectionRepository sectionRepository, UserRepository userRepository) {
        super(sectionRepository, SectionMapper.INSTANCE);
        this.sectionRepository = sectionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public SectionDto create(SectionDto dto) throws EntityNotFoundException
    {

        Section entity = SectionMapper.INSTANCE.dtoToEntity(dto);
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
            LOGGER.warn("The user of the section must be an instructor!");
            throw new EntityNotFoundException();
        }
        return super.create(SectionMapper.INSTANCE.entityToDto(entity));
    }
}