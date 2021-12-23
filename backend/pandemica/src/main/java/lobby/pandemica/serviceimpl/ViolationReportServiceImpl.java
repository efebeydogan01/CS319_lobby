package lobby.pandemica.serviceimpl;

import lobby.pandemica.db.User;
import lobby.pandemica.db.ViolationReport;
import lobby.pandemica.dto.ViolationReportDto;
import lobby.pandemica.repository.UserRepository;
import lobby.pandemica.repository.base.BaseRepository;
import lobby.pandemica.service.ViolationFormService;
import lobby.pandemica.serviceimpl.base.BaseServiceImpl;
import lobby.pandemica.serviceimpl.mapper.UserMapper;
import lobby.pandemica.serviceimpl.mapper.base.BaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
public class ViolationReportServiceImpl extends BaseServiceImpl<ViolationReport, ViolationReportDto> implements ViolationFormService
{
	private static final Logger LOGGER = LoggerFactory.getLogger(ViolationReportServiceImpl.class);
	private final UserMapper userMapper;
	private final UserRepository userRepository;
	public ViolationReportServiceImpl(BaseRepository<ViolationReport, UUID> baseRepository,
									  BaseMapper<ViolationReport, ViolationReportDto> baseMapper,
									  UserRepository userRepository, UserMapper userMapper)
	{
		super(baseRepository, baseMapper);
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	@Override
	public ViolationReportDto create(ViolationReportDto dto) throws EntityNotFoundException
	{
		User user = userRepository.getById(dto.getUser().getId());
		dto.setUser(userMapper.entityToDto(user));
		return super.create(dto);
	}
}
