package lobby.pandemica.serviceimpl;

import lobby.pandemica.db.MedicalEmployee;
import lobby.pandemica.db.Student;
import lobby.pandemica.db.User;
import lobby.pandemica.dto.MedicalEmployeeDto;
import lobby.pandemica.repository.MedicalEmployeeRepository;
import lobby.pandemica.repository.UserRepository;
import lobby.pandemica.service.MedicalEmployeeService;
import lobby.pandemica.serviceimpl.base.BaseServiceImpl;
import lobby.pandemica.serviceimpl.mapper.MedicalEmployeeMapper;
import lobby.pandemica.serviceimpl.mapper.StudentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Service
public class MedicalEmployeeServiceImpl extends BaseServiceImpl<MedicalEmployee, MedicalEmployeeDto> implements MedicalEmployeeService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MedicalEmployeeServiceImpl.class);

    private final MedicalEmployeeRepository medicalEmployeeRepository;
    private final UserRepository userRepository;

    public MedicalEmployeeServiceImpl(MedicalEmployeeRepository medicalEmployeeRepository, UserRepository userRepository) {
        super(medicalEmployeeRepository, MedicalEmployeeMapper.INSTANCE);
        this.medicalEmployeeRepository = medicalEmployeeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public MedicalEmployeeDto create(MedicalEmployeeDto dto) throws EntityNotFoundException
    {
        MedicalEmployee entity = MedicalEmployeeMapper.INSTANCE.dtoToEntity(dto);
        if (entity == null) {
            LOGGER.warn("The entity to save cannot be empty!");
            throw new EntityNotFoundException();
        }
        Optional<User> infoUser = userRepository.findByBilkentId(dto.getUser().getBilkentId());
        if (!infoUser.isPresent())
        {
            LOGGER.warn("The user of the medical employee cannot be empty!");
            throw new EntityNotFoundException();
        }
        entity.setUser(infoUser.get());
        return super.create(MedicalEmployeeMapper.INSTANCE.entityToDto(entity));
    }

    @Override
    public MedicalEmployeeDto getUserWithRole(UUID userId)
    {
        Optional<MedicalEmployee> optionalMedicalEmployee = medicalEmployeeRepository.findByUserId(userId);
        if (!optionalMedicalEmployee.isPresent())
        {
            LOGGER.warn("The entity to find does not exist!");
            throw new EntityNotFoundException();
        }
        return MedicalEmployeeMapper.INSTANCE.entityToDto(optionalMedicalEmployee.get());
    }
}