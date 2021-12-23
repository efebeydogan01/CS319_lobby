package lobby.pandemica.serviceimpl;

import lobby.pandemica.db.VaccineInformation;
import lobby.pandemica.dto.VaccineInformationDto;
import lobby.pandemica.repository.UserRepository;
import lobby.pandemica.repository.VaccineInformationRepository;
import lobby.pandemica.repository.base.BaseRepository;
import lobby.pandemica.service.VaccineInformationService;
import lobby.pandemica.serviceimpl.base.BaseServiceImpl;
import lobby.pandemica.serviceimpl.mapper.UserMapper;
import lobby.pandemica.serviceimpl.mapper.VaccineInformationMapper;
import lobby.pandemica.serviceimpl.mapper.base.BaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VaccineInformationServiceImpl extends BaseServiceImpl<VaccineInformation, VaccineInformationDto> implements VaccineInformationService
{
	private static final Logger LOGGER = LoggerFactory.getLogger(VaccineInformationServiceImpl.class);

	private final VaccineInformationRepository vaccineInformationRepository;

	private final VaccineInformationMapper vaccineInformationMapper;

	public VaccineInformationServiceImpl(BaseRepository<VaccineInformation, UUID> baseRepository, BaseMapper<VaccineInformation, VaccineInformationDto> baseMapper, VaccineInformationRepository vaccineInformationRepository, VaccineInformationMapper vaccineInformationMapper)
	{
		super(baseRepository, baseMapper);
		this.vaccineInformationRepository = vaccineInformationRepository;
		this.vaccineInformationMapper = vaccineInformationMapper;
	}

	@Override
	public List<String> upload(MultipartFile multipartFile, UUID id)
	{
		return new ArrayList<>();
	}
}
