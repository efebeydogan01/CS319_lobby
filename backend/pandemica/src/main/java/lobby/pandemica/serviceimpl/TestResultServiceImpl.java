package lobby.pandemica.serviceimpl;
import lobby.pandemica.db.TestResult;
import lobby.pandemica.dto.TestResultDto;
import lobby.pandemica.repository.TestResultRepository;
import lobby.pandemica.service.TestResultService;
import lobby.pandemica.serviceimpl.base.BaseServiceImpl;
import lobby.pandemica.serviceimpl.mapper.TestResultMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TestResultServiceImpl extends BaseServiceImpl<TestResult, TestResultDto> implements TestResultService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(TestResultServiceImpl.class);

    private final TestResultRepository testResultRepository;

    public TestResultServiceImpl(TestResultRepository testResultRepository) {
        super(testResultRepository, TestResultMapper.INSTANCE);
        this.testResultRepository = testResultRepository;
    }
}