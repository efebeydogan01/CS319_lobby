package lobby.pandemica.service;

import lobby.pandemica.dto.MedicalEmployeeDto;
import lobby.pandemica.dto.StudentDto;
import lobby.pandemica.service.base.BaseCrudService;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface MedicalEmployeeService extends BaseCrudService<MedicalEmployeeDto>
{
	public MedicalEmployeeDto getUserWithRole(UUID userId);
}
