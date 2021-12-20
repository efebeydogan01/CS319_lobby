package lobby.pandemica.serviceimpl;

import lobby.pandemica.db.CovidInformation;
import lobby.pandemica.dto.CovidInformationDto;
import lobby.pandemica.repository.CovidInformationRepository;
import lobby.pandemica.service.CovidInformationService;
import lobby.pandemica.serviceimpl.base.BaseServiceImpl;
import lobby.pandemica.serviceimpl.mapper.CovidInformationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CovidInformationServiceImpl extends BaseServiceImpl<CovidInformation, CovidInformationDto> implements CovidInformationService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final CovidInformationRepository covidInformationRepository;

    public CovidInformationServiceImpl(CovidInformationRepository covidInformationRepository) {
        super(covidInformationRepository, CovidInformationMapper.INSTANCE);
        this.covidInformationRepository = covidInformationRepository;
    }
}
