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

@Service
public class CovidInformationServiceImpl extends BaseServiceImpl<CovidInformation, CovidInformationDto> implements CovidInformationService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final CovidInformationRepository covidInformationRepository;
    private final UserRepository userRepository;

    public CovidInformationServiceImpl(CovidInformationRepository covidInformationRepository,UserRepository userRepository) {
        super(covidInformationRepository, CovidInformationMapper.INSTANCE);
        this.covidInformationRepository = covidInformationRepository;
        this.userRepository = userRepository;
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
}
