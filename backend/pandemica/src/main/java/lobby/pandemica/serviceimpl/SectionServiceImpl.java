package lobby.pandemica.serviceimpl;

import com.sun.xml.bind.v2.model.core.ID;
import lobby.pandemica.db.AcademicPersonnel;
import lobby.pandemica.db.Role;
import lobby.pandemica.db.Section;
import lobby.pandemica.db.User;
import lobby.pandemica.dto.AcademicPersonnelDto;
import lobby.pandemica.dto.SectionDto;
import lobby.pandemica.dto.UserDto;
import lobby.pandemica.repository.AcademicPersonnelRepository;
import lobby.pandemica.repository.SectionRepository;
import lobby.pandemica.repository.UserRepository;
import lobby.pandemica.service.SectionService;
import lobby.pandemica.serviceimpl.base.BaseServiceImpl;
import lobby.pandemica.serviceimpl.mapper.AcademicPersonnelMapper;
import lobby.pandemica.serviceimpl.mapper.SectionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Service
public class SectionServiceImpl extends BaseServiceImpl<Section, SectionDto> implements SectionService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SectionServiceImpl.class);

    private final SectionRepository sectionRepository;
    private final UserRepository userRepository;
    private final SectionMapper sectionMapper;
    private final AcademicPersonnelRepository academicPersonnelRepository;

    public SectionServiceImpl(SectionRepository sectionRepository, SectionMapper sectionMapper,
                              UserRepository userRepository, AcademicPersonnelRepository academicPersonnelRepository) {
        super(sectionRepository, SectionMapper.INSTANCE);
        this.sectionRepository = sectionRepository;
        this.userRepository = userRepository;
        this.sectionMapper = sectionMapper;
        this.academicPersonnelRepository = academicPersonnelRepository;
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
        return SectionMapper.INSTANCE.entityToDto(sectionRepository.save(entity));
    }
}
