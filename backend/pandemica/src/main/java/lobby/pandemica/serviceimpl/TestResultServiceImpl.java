package lobby.pandemica.serviceimpl;
import lobby.pandemica.db.CovidInformation;
import lobby.pandemica.db.TestResult;
import lobby.pandemica.db.User;
import lobby.pandemica.dto.TestResultDto;
import lobby.pandemica.repository.CovidInformationRepository;
import lobby.pandemica.repository.TestResultRepository;
import lobby.pandemica.repository.UserRepository;
import lobby.pandemica.service.TestResultService;
import lobby.pandemica.serviceimpl.base.BaseServiceImpl;
import lobby.pandemica.serviceimpl.mapper.TestResultMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
public class TestResultServiceImpl extends BaseServiceImpl<TestResult, TestResultDto> implements TestResultService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(TestResultServiceImpl.class);

    private final TestResultRepository testResultRepository;
    private final UserRepository userRepository;
    private final CovidInformationRepository covidInformationRepository;

    public TestResultServiceImpl(TestResultRepository testResultRepository,
                                 UserRepository userRepository,
                                 CovidInformationRepository covidInformationRepository) {
        super(testResultRepository, TestResultMapper.INSTANCE);
        this.testResultRepository = testResultRepository;
        this.userRepository = userRepository;
        this.covidInformationRepository = covidInformationRepository;
    }

    @Override
    public List<TestResultDto> getTestResults(UUID userId)
    {
        return TestResultMapper.INSTANCE.entityListToDtoList(testResultRepository.findAllByUserId(userId));
    }

    @Override
    public TestResultDto create(TestResultDto dto) throws EntityNotFoundException
    {
        CovidInformation covidInformation = covidInformationRepository.getByUserId(dto.getUser().getId());
        covidInformation.setStatus(dto.getResult());
        covidInformationRepository.save(covidInformation);
        return super.create(dto);
    }
}