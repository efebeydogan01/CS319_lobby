package lobby.pandemica.service;

import lobby.pandemica.dto.VaccineInformationDto;
import lobby.pandemica.service.base.BaseCrudService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface VaccineInformationService extends BaseCrudService<VaccineInformationDto>
{
	List<VaccineInformationDto> upload(MultipartFile multipartFile, UUID id);
}
