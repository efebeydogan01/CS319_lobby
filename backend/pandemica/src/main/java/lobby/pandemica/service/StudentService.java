package lobby.pandemica.service;

import lobby.pandemica.dto.StudentDto;
import lobby.pandemica.service.base.BaseCrudService;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface StudentService extends BaseCrudService<StudentDto>
{
	public StudentDto getUserWithRole(UUID userId);
}
