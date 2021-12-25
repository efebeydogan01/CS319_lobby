package lobby.pandemica.serviceimpl;

import lobby.pandemica.db.CovidInformation;
import lobby.pandemica.db.User;
import lobby.pandemica.db.VaccineInformation;
import lobby.pandemica.dto.CovidInformationDto;
import lobby.pandemica.dto.VaccineInformationDto;
import lobby.pandemica.repository.UserRepository;
import lobby.pandemica.repository.VaccineInformationRepository;
import lobby.pandemica.repository.base.BaseRepository;
import lobby.pandemica.service.VaccineInformationService;
import lobby.pandemica.serviceimpl.base.BaseServiceImpl;
import lobby.pandemica.serviceimpl.mapper.CovidInformationMapper;
import lobby.pandemica.serviceimpl.mapper.UserMapper;
import lobby.pandemica.serviceimpl.mapper.VaccineInformationMapper;
import lobby.pandemica.serviceimpl.mapper.base.BaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VaccineInformationServiceImpl extends BaseServiceImpl<VaccineInformation, VaccineInformationDto> implements VaccineInformationService
{
	private static final Logger LOGGER = LoggerFactory.getLogger(VaccineInformationServiceImpl.class);

	private final VaccineInformationRepository vaccineInformationRepository;
	private final UserRepository userRepository;
	private final VaccineInformationMapper vaccineInformationMapper;

	public VaccineInformationServiceImpl(VaccineInformationRepository vaccineInformationRepository, UserRepository userRepository,
										 VaccineInformationMapper vaccineInformationMapper)
	{
		super(vaccineInformationRepository, VaccineInformationMapper.INSTANCE );
		this.vaccineInformationRepository = vaccineInformationRepository;
		this.userRepository = userRepository;
		this.vaccineInformationMapper = vaccineInformationMapper;
	}

	@Override
	public VaccineInformationDto create(VaccineInformationDto dto) throws EntityNotFoundException
	{

		VaccineInformation entity = VaccineInformationMapper.INSTANCE.dtoToEntity(dto);
		if (entity == null) {
			LOGGER.warn("The entity to save cannot be empty!");
			throw new EntityNotFoundException();
		}
		Optional<User> infoUser = userRepository.findByBilkentId(dto.getUser().getBilkentId());
		if (!infoUser.isPresent())
		{
			LOGGER.warn("The user of the covid information cannot be empty!");
			throw new EntityNotFoundException();
		}
		entity.setUser(infoUser.get());

		return VaccineInformationMapper.INSTANCE.entityToDto(vaccineInformationRepository.save(entity));
	}

	@Override
	public List<VaccineInformationDto> get(UUID userId) throws EntityNotFoundException
	{
		Optional<User> infoUser = userRepository.findById(userId);
		if (!infoUser.isPresent())
		{
			LOGGER.warn("The user of the covid information cannot be empty!");
			throw new EntityNotFoundException();
		}

		List<VaccineInformation> vaccineInformationList = vaccineInformationRepository.findAllByUserId(userId);
		List<VaccineInformationDto> vaccineInformationDtoList = new ArrayList<>();
		for (int i = 0; i < vaccineInformationList.size(); i++) {
			vaccineInformationDtoList.add(vaccineInformationMapper.entityToDto(vaccineInformationList.get(i)));
		}

		return vaccineInformationDtoList;
	}

	@Override
	public List<String> upload(MultipartFile multipartFile, UUID id)
	{
		return new ArrayList<>();
	}
}
