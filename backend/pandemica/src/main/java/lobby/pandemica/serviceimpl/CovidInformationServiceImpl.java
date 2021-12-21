package lobby.pandemica.serviceimpl;

import lobby.pandemica.db.CovidInformation;
import lobby.pandemica.db.User;
import lobby.pandemica.dto.CovidInformationDto;
import lobby.pandemica.repository.CovidInformationRepository;
import lobby.pandemica.repository.UserRepository;
import lobby.pandemica.service.CovidInformationService;
import lobby.pandemica.serviceimpl.base.BaseServiceImpl;
import lobby.pandemica.serviceimpl.mapper.CovidInformationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Service
public class CovidInformationServiceImpl extends BaseServiceImpl<CovidInformation, CovidInformationDto> implements CovidInformationService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CovidInformationServiceImpl.class);

    private final CovidInformationRepository covidInformationRepository;
    private final UserRepository userRepository;
    private final CovidInformationMapper covidInformationMapper;

    public CovidInformationServiceImpl(CovidInformationRepository covidInformationRepository,
                                       UserRepository userRepository, CovidInformationMapper covidInformationMapper) {
        super(covidInformationRepository, CovidInformationMapper.INSTANCE);
        this.covidInformationRepository = covidInformationRepository;
        this.userRepository = userRepository;
        this.covidInformationMapper = covidInformationMapper;
    }

    @Override
    public CovidInformationDto create(CovidInformationDto dto) throws EntityNotFoundException
    {

        CovidInformation entity = CovidInformationMapper.INSTANCE.dtoToEntity(dto);
        if (entity == null) {
            LOGGER.warn("The entity to save cannot be empty!");
            throw new EntityNotFoundException();
        }
        Optional<User> infoUser = userRepository.findByBilkentId(dto.getUser().getBilkentId());
        if (!infoUser.isPresent())
        {
            LOGGER.warn("The user of the covid information cannot be empty!");
            throw new EntityNotFoundException();
        }
        entity.setUser(infoUser.get());
        return super.create(CovidInformationMapper.INSTANCE.entityToDto(entity));
    }

    public CovidInformationDto get(UUID userId) throws EntityNotFoundException
    {
        if (userId == null)
        {
            LOGGER.warn("The id cannot be empty!");
            throw new EntityNotFoundException();
        }
        Optional<CovidInformation> covidInformationOptional = covidInformationRepository.findByUserId(userId);
        if (!covidInformationOptional.isPresent()) {
            LOGGER.warn("No such covid information!");
            throw new EntityNotFoundException();
        }
        return covidInformationMapper.entityToDto(covidInformationOptional.get());
    }
}
