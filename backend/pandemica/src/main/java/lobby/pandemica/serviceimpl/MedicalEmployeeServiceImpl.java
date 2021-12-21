package lobby.pandemica.serviceimpl;

import lobby.pandemica.db.MedicalEmployee;
import lobby.pandemica.dto.MedicalEmployeeDto;
import lobby.pandemica.repository.MedicalEmployeeRepository;
import lobby.pandemica.service.MedicalEmployeeService;
import lobby.pandemica.serviceimpl.base.BaseServiceImpl;
import lobby.pandemica.serviceimpl.mapper.MedicalEmployeeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MedicalEmployeeServiceImpl extends BaseServiceImpl<MedicalEmployee, MedicalEmployeeDto> implements MedicalEmployeeService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MedicalEmployeeServiceImpl.class);

    private final MedicalEmployeeRepository medicalEmployeeRepository;

    public MedicalEmployeeServiceImpl(MedicalEmployeeRepository medicalEmployeeRepository) {
        super(medicalEmployeeRepository, MedicalEmployeeMapper.INSTANCE);
        this.medicalEmployeeRepository = medicalEmployeeRepository;
    }
}