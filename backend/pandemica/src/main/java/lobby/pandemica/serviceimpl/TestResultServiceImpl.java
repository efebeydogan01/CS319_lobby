package lobby.pandemica.serviceimpl;
import lobby.pandemica.db.*;
import lobby.pandemica.dto.TestResultDto;
import lobby.pandemica.repository.*;
import lobby.pandemica.service.TestResultService;
import lobby.pandemica.serviceimpl.base.BaseServiceImpl;
import lobby.pandemica.serviceimpl.mapper.TestResultMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TestResultServiceImpl extends BaseServiceImpl<TestResult, TestResultDto> implements TestResultService
{
    private final TestResultRepository testResultRepository;
    private final CovidInformationRepository covidInformationRepository;
    private final NeighborRepository neighborRepository;
    private final StudentRepository studentRepository;

    public TestResultServiceImpl(TestResultRepository testResultRepository,
                                 CovidInformationRepository covidInformationRepository,
                                 NeighborRepository neighborRepository,
                                 StudentRepository studentRepository) {
        super(testResultRepository, TestResultMapper.INSTANCE);
        this.testResultRepository = testResultRepository;
        this.covidInformationRepository = covidInformationRepository;
        this.neighborRepository = neighborRepository;
        this.studentRepository = studentRepository;
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
        if (dto.getResult().equalsIgnoreCase("POSITIVE"))
        {
            checkNeighbors(dto.getUser().getId());
        }
        return super.create(dto);
    }

    private void checkNeighbors(UUID userId){
        Optional<Student> student = studentRepository.findByUserId(userId);
        List<UUID> neighborIdList = new ArrayList<>();

        if (!student.isPresent())
        {
            return;
        }
        UUID studentId = student.get().getId();
        List<Neighbor> neighborList = neighborRepository.findAllByFirstStudent_Id(studentId);

        for (Neighbor neighbor: neighborList)
        {
            neighborIdList.add(neighbor.getSecondStudent().getUser().getId());
        }
        for (UUID id: neighborIdList)
        {
            CovidInformation covidInformation = covidInformationRepository.getByUserId(id);
            if (covidInformation.getStatus().equalsIgnoreCase("NEGATIVE"))
            {
                covidInformation.setStatus("RISKY");
                covidInformationRepository.save(covidInformation);
            }
        }
    }
}