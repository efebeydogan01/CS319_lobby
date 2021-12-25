package lobby.pandemica.serviceimpl;

import lobby.pandemica.db.*;
import lobby.pandemica.dto.NeighborDto;
import lobby.pandemica.repository.CovidInformationRepository;
import lobby.pandemica.repository.NeighborRepository;
import lobby.pandemica.repository.StudentRepository;
import lobby.pandemica.repository.UserRepository;
import lobby.pandemica.service.NeighborService;
import lobby.pandemica.serviceimpl.base.BaseServiceImpl;
import lobby.pandemica.serviceimpl.mapper.NeighborMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NeighborServiceImpl extends BaseServiceImpl<Neighbor, NeighborDto> implements NeighborService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(NeighborServiceImpl.class);

    private final NeighborRepository neighborRepository;
    private final StudentRepository studentRepository;
    private final CovidInformationRepository covidInformationRepository;
    private final UserRepository userRepository;

    public NeighborServiceImpl(NeighborRepository neighborRepository, StudentRepository studentRepository,
                               CovidInformationRepository covidInformationRepository,
                               UserRepository userRepository) {
        super(neighborRepository, NeighborMapper.INSTANCE);
        this.neighborRepository = neighborRepository;
        this.studentRepository = studentRepository;
        this.covidInformationRepository = covidInformationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Boolean getRiskStatus(UUID id)
    {
        Optional<Student> dbStudent = studentRepository.findByUserId(id);
        if (!dbStudent.isPresent())
        {
            return null;
        }
        UUID studentId = dbStudent.get().getId();
        User dbUser = userRepository.getById(id);
        if (!dbUser.getRole().equalsIgnoreCase(Role.ROLES.STUDENT.name()))
        {
            return null;
        }
        int noOfNeighbors = neighborRepository.countNeighborsByFirstStudentId(studentId);
        if (noOfNeighbors == 0)
        {
            return Boolean.TRUE;
        }
        List<Neighbor> list = neighborRepository.findAllByFirstStudent_Id(studentId);
        List<Student> neighbors = new ArrayList<>();
        for (Neighbor neighbor: list)
        {
            neighbors.add(neighbor.getSecondStudent());
        }
        List<User> neighborsUsers = new ArrayList<>();
        for (Student student: neighbors)
        {
            Optional<Student> optionalStudent = studentRepository.findById(student.getId());
            optionalStudent.ifPresent(value -> neighborsUsers.add(value.getUser()));
        }
        for (User user: neighborsUsers)
        {
            Optional<CovidInformation> covidInformation = covidInformationRepository.findByUserId(user.getId());
            if (covidInformation.isPresent() && covidInformation.get().getStatus().equalsIgnoreCase(Status.RISK.POSITIVE.name()))
            {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    @Override
    public NeighborDto create(NeighborDto dto) throws EntityNotFoundException
    {
        Neighbor entity = NeighborMapper.INSTANCE.dtoToEntity(dto);
        if (entity == null) {
            LOGGER.warn("The entity to save cannot be empty!");
            throw new EntityNotFoundException();
        }
        NeighborDto otherNeighbor = new NeighborDto();
        otherNeighbor.setFirstStudent(dto.getSecondStudent());
        otherNeighbor.setSecondStudent(dto.getFirstStudent());
        otherNeighbor.setSection(dto.getSection());
        super.create(otherNeighbor);
        return super.create(dto);
    }
}
