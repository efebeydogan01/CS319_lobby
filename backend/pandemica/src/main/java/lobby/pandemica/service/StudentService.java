package lobby.pandemica.service;

import lobby.pandemica.db.SectionWithSeats;
import lobby.pandemica.dto.StudentDto;
import lobby.pandemica.service.base.BaseCrudService;

import java.util.List;
import java.util.UUID;

public interface StudentService extends BaseCrudService<StudentDto>
{
	public StudentDto getUserWithRole(UUID userId);
	public List<SectionWithSeats> getSectionsWithSeats(UUID studentId);
}
