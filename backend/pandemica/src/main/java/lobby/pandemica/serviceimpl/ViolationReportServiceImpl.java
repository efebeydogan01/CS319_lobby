package lobby.pandemica.serviceimpl;

import lobby.pandemica.db.User;
import lobby.pandemica.db.ViolationReport;
import lobby.pandemica.dto.ViolationReportDto;
import lobby.pandemica.repository.UserRepository;
import lobby.pandemica.repository.ViolationReportRepository;
import lobby.pandemica.repository.base.BaseRepository;
import lobby.pandemica.service.ViolationFormService;
import lobby.pandemica.serviceimpl.base.BaseServiceImpl;
import lobby.pandemica.serviceimpl.mapper.UserMapper;
import lobby.pandemica.serviceimpl.mapper.ViolationFormMapper;
import lobby.pandemica.serviceimpl.mapper.base.BaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
public class ViolationReportServiceImpl extends BaseServiceImpl<ViolationReport, ViolationReportDto> implements ViolationFormService
{
	private static final Logger LOGGER = LoggerFactory.getLogger(ViolationReportServiceImpl.class);
	private final ViolationReportRepository violationReportRepository;
	private final UserMapper userMapper;
	private final UserRepository userRepository;

	public ViolationReportServiceImpl(BaseRepository<ViolationReport, UUID> baseRepository,
									  BaseMapper<ViolationReport, ViolationReportDto> baseMapper,
									  ViolationReportRepository violationReportRepository,
									  UserMapper userMapper, UserRepository userRepository)
	{
		super(baseRepository, baseMapper);
		this.violationReportRepository = violationReportRepository;
		this.userMapper = userMapper;
		this.userRepository = userRepository;
	}

	@Override
	public ViolationReportDto create(ViolationReportDto dto) throws EntityNotFoundException
	{
		User user = userRepository.getById(dto.getUser().getId());
		dto.setUser(userMapper.entityToDto(user));
		return super.create(dto);
	}

	@Override
	public List<ViolationReportDto> readAllFromUser(UUID id)
	{
		return ViolationFormMapper.INSTANCE.entityListToDtoList(violationReportRepository.findAllByUserId(id));
	}
}
