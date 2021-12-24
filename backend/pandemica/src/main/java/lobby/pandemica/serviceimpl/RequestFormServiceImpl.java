package lobby.pandemica.serviceimpl;

import lobby.pandemica.db.RequestForm;
import lobby.pandemica.db.User;
import lobby.pandemica.dto.RequestFormDto;
import lobby.pandemica.repository.RequestFormRepository;
import lobby.pandemica.repository.UserRepository;
import lobby.pandemica.repository.base.BaseRepository;
import lobby.pandemica.service.RequestFormService;
import lobby.pandemica.serviceimpl.base.BaseServiceImpl;
import lobby.pandemica.serviceimpl.mapper.RequestFormMapper;
import lobby.pandemica.serviceimpl.mapper.UserMapper;
import lobby.pandemica.serviceimpl.mapper.base.BaseMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
public class RequestFormServiceImpl extends BaseServiceImpl<RequestForm, RequestFormDto> implements RequestFormService
{
	private final RequestFormRepository requestFormRepository;
	private final UserRepository userRepository;

	public RequestFormServiceImpl(BaseRepository<RequestForm, UUID> baseRepository, BaseMapper<RequestForm, RequestFormDto> baseMapper, RequestFormRepository requestFormRepository, UserRepository userRepository)
	{
		super(baseRepository, baseMapper);
		this.requestFormRepository = requestFormRepository;
		this.userRepository = userRepository;
	}

	@Override
	public List<RequestFormDto> readAllFromUser(UUID id)
	{
		return RequestFormMapper.INSTANCE.entityListToDtoList(requestFormRepository.findAllByUserId(id));
	}

	@Override
	public RequestFormDto create(RequestFormDto dto) throws EntityNotFoundException
	{
		User user = userRepository.getById(dto.getUser().getId());
		dto.setUser(UserMapper.INSTANCE.entityToDto(user));
		return super.create(dto);
	}
}
