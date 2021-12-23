package lobby.pandemica.serviceimpl;

import lobby.pandemica.db.AcademicPersonnel;
import lobby.pandemica.db.Announcement;
import lobby.pandemica.dto.AnnouncementDto;
import lobby.pandemica.dto.UserDto;
import lobby.pandemica.repository.AcademicPersonnelRepository;
import lobby.pandemica.repository.AnnouncementRepository;
import lobby.pandemica.repository.UserRepository;
import lobby.pandemica.service.AcademicPersonnelService;
import lobby.pandemica.service.AnnouncementService;
import lobby.pandemica.serviceimpl.base.BaseServiceImpl;
import lobby.pandemica.serviceimpl.mapper.AcademicPersonnelMapper;
import lobby.pandemica.serviceimpl.mapper.AnnouncementMapper;
import lobby.pandemica.serviceimpl.mapper.CovidInformationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sun.java2d.loops.FillRect;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Service
public class AnnouncementServiceImpl extends BaseServiceImpl<Announcement, AnnouncementDto> implements AnnouncementService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AcademicPersonnelServiceImpl.class);

    private final AnnouncementMapper announcementMapper;
    private final AnnouncementRepository announcementRepository;

    public AnnouncementServiceImpl(AnnouncementRepository announcementRepository, AnnouncementMapper announcementMapper) {
        super(announcementRepository, AnnouncementMapper.INSTANCE);
        this.announcementRepository = announcementRepository;
        this.announcementMapper = announcementMapper;
    }
    @Override
    public AnnouncementDto create(AnnouncementDto dto) throws EntityNotFoundException
    {

        Announcement entity = AnnouncementMapper.INSTANCE.dtoToEntity(dto);
        if (entity == null) {
            LOGGER.warn("The entity to save cannot be empty!");
            throw new EntityNotFoundException();
        }
        return super.create(AnnouncementMapper.INSTANCE.entityToDto(entity));
    }

    public AnnouncementDto get(UUID id) throws EntityNotFoundException
    {
        if (id == null)
        {
            LOGGER.warn("The id cannot be empty!");
            throw new EntityNotFoundException();
        }
        Optional<Announcement> announcementOptional = announcementRepository.findById(id);
        if (!announcementOptional.isPresent()) {
            LOGGER.warn("No such announcement!");
            throw new EntityNotFoundException();
        }
        return announcementMapper.entityToDto(announcementOptional.get());
    }

}