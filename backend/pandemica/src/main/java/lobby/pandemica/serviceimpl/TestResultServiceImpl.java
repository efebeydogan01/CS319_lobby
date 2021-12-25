package lobby.pandemica.serviceimpl;
import lobby.pandemica.db.*;
import lobby.pandemica.dto.TestResultDto;
import lobby.pandemica.repository.*;
import lobby.pandemica.service.NotificationService;
import lobby.pandemica.service.TestResultService;
import lobby.pandemica.serviceimpl.base.BaseServiceImpl;
import lobby.pandemica.serviceimpl.mapper.TestResultMapper;
import lobby.pandemica.serviceimpl.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
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
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public TestResultServiceImpl(TestResultRepository testResultRepository,
                                 CovidInformationRepository covidInformationRepository,
                                 NeighborRepository neighborRepository,
                                 StudentRepository studentRepository,
                                 NotificationRepository notificationRepository,
                                 UserRepository userRepository) {
        super(testResultRepository, TestResultMapper.INSTANCE);
        this.testResultRepository = testResultRepository;
        this.covidInformationRepository = covidInformationRepository;
        this.neighborRepository = neighborRepository;
        this.studentRepository = studentRepository;
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
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
            // if a test result comes as positive, the neighbors of that user should be marked as risky
            checkNeighbors(dto.getUser().getId(), dto);
        }
        return super.create(dto);
    }

    private void checkNeighbors(UUID userId, TestResultDto dto){
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
            // If a neighbor is negative, their status should be changed to risky, otherwise it should not be changed
            if (covidInformation.getStatus().equalsIgnoreCase("NEGATIVE"))
            {
                covidInformation.setStatus("RISKY");
                covidInformationRepository.save(covidInformation);
                // After changing the status of the neighbor, a notification is sent to that neighbor stating they should
                // make a test appointment
                Notification notification = new Notification();
                notification.setUser(UserMapper.INSTANCE.dtoToEntity(dto.getUser()));
                notification.setTitle("Neighbor Contacted Virus!");
                notification.setMessage("One of your neighbors has tested positive for COVID and your status has been " +
                        "changed to risky. Please make a test appointment as soon as possible.");
                notification.setCreatedOn(Instant.now());
                notification.setReceiver(userRepository.getById(covidInformation.getUser().getId()));
                notificationRepository.save(notification);
            }
        }
    }
}