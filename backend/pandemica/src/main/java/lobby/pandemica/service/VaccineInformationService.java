package lobby.pandemica.service;

import lobby.pandemica.db.VaccineInformation;
import lobby.pandemica.dto.VaccineInformationDto;
import lobby.pandemica.service.base.BaseCrudService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface VaccineInformationService extends BaseCrudService<VaccineInformationDto>
{
	List<String> upload(MultipartFile multipartFile, UUID id) throws IOException;
	List<VaccineInformationDto> get(UUID userId);
}
