package lobby.pandemica.serviceimpl;

import lobby.pandemica.db.CovidInformation;
import lobby.pandemica.db.ExtendedCovidInformation;
import lobby.pandemica.db.User;
import lobby.pandemica.db.VaccineInformation;
import lobby.pandemica.dto.CovidInformationDto;
import lobby.pandemica.repository.CovidInformationRepository;
import lobby.pandemica.repository.UserRepository;
import lobby.pandemica.repository.VaccineInformationRepository;
import lobby.pandemica.service.CovidInformationService;
import lobby.pandemica.serviceimpl.base.BaseServiceImpl;
import lobby.pandemica.serviceimpl.mapper.CovidInformationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CovidInformationServiceImpl extends BaseServiceImpl<CovidInformation, CovidInformationDto> implements CovidInformationService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CovidInformationServiceImpl.class);

    private final CovidInformationRepository covidInformationRepository;
    private final UserRepository userRepository;
    private final CovidInformationMapper covidInformationMapper;
    private final VaccineInformationRepository vaccineInformationRepository;

    public CovidInformationServiceImpl(CovidInformationRepository covidInformationRepository, CovidInformationMapper covidInformationMapper,
                                       UserRepository userRepository, VaccineInformationRepository vaccineInformationRepository) {
        super(covidInformationRepository, CovidInformationMapper.INSTANCE);
        this.covidInformationRepository = covidInformationRepository;
        this.userRepository = userRepository;
        this.covidInformationMapper = covidInformationMapper;
        this.vaccineInformationRepository = vaccineInformationRepository;
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

    public List<ExtendedCovidInformation> getExtendedCovidInformation() throws EntityNotFoundException
    {
        List<User> infoUsers = userRepository.findAll();
        if (infoUsers == null) {
            LOGGER.warn("There exists no user!");
            throw new EntityNotFoundException();
        }

        List<ExtendedCovidInformation> extendedCovidInformationList = new ArrayList<>();
        for (int i = 0; i < infoUsers.size(); i++) {
            User user = infoUsers.get(i);

            Optional<CovidInformation> covidInformationOptional = covidInformationRepository.findByUserId(user.getId());
            if (!covidInformationOptional.isPresent()) {
                LOGGER.warn("No such covid information!");
                throw new EntityNotFoundException();
            }

            List<VaccineInformation> vaccineInformationList = vaccineInformationRepository.findAllByUserId(user.getId());

            ExtendedCovidInformation extendedCovidInformation = new ExtendedCovidInformation();
            extendedCovidInformation.setCovidInformation(covidInformationOptional.get());
            extendedCovidInformation.setVaccinationCount(vaccineInformationList.size());
            extendedCovidInformationList.add(extendedCovidInformation);
        }
        return extendedCovidInformationList;
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
