package lobby.pandemica.service;

import lobby.pandemica.dto.StudentDto;
import lobby.pandemica.dto.TestResultDto;
import lobby.pandemica.service.base.BaseCrudService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface TestResultService extends BaseCrudService<TestResultDto>
{
	List<TestResultDto> getTestResults(UUID userId);
}
