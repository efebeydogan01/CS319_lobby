package lobby.pandemica.serviceimpl;

import lobby.pandemica.db.RequestForm;
import lobby.pandemica.dto.RequestFormDto;
import lobby.pandemica.repository.RequestFormRepository;
import lobby.pandemica.repository.base.BaseRepository;
import lobby.pandemica.service.RequestFormService;
import lobby.pandemica.serviceimpl.base.BaseServiceImpl;
import lobby.pandemica.serviceimpl.mapper.RequestFormMapper;
import lobby.pandemica.serviceimpl.mapper.base.BaseMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RequestFormServiceImpl extends BaseServiceImpl<RequestForm, RequestFormDto> implements RequestFormService
{
	private final RequestFormRepository requestFormRepository;

	public RequestFormServiceImpl(BaseRepository<RequestForm, UUID> baseRepository, BaseMapper<RequestForm, RequestFormDto> baseMapper, RequestFormRepository requestFormRepository)
	{
		super(baseRepository, baseMapper);
		this.requestFormRepository = requestFormRepository;
	}

	@Override
	public List<RequestFormDto> readAllFromUser(UUID id)
	{
		return RequestFormMapper.INSTANCE.entityListToDtoList(requestFormRepository.findAllByUserId(id));
	}
}
