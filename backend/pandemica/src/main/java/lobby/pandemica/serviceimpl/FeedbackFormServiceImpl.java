package lobby.pandemica.serviceimpl;

import lobby.pandemica.db.FeedbackForm;
import lobby.pandemica.db.User;
import lobby.pandemica.dto.FeedbackFormDto;
import lobby.pandemica.repository.FeedbackFormRepository;
import lobby.pandemica.repository.UserRepository;
import lobby.pandemica.repository.base.BaseRepository;
import lobby.pandemica.service.FeedbackFormService;
import lobby.pandemica.serviceimpl.base.BaseServiceImpl;
import lobby.pandemica.serviceimpl.mapper.FeedbackFormMapper;
import lobby.pandemica.serviceimpl.mapper.UserMapper;
import lobby.pandemica.serviceimpl.mapper.base.BaseMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
public class FeedbackFormServiceImpl extends BaseServiceImpl<FeedbackForm, FeedbackFormDto> implements FeedbackFormService
{
	private final FeedbackFormRepository feedbackFormRepository;
	private final UserRepository userRepository;
	public FeedbackFormServiceImpl(BaseRepository<FeedbackForm, UUID> baseRepository,
								   BaseMapper<FeedbackForm, FeedbackFormDto> baseMapper,
								   FeedbackFormRepository feedbackFormRepository,
								   UserRepository userRepository)
	{
		super(baseRepository, baseMapper);
		this.feedbackFormRepository = feedbackFormRepository;
		this.userRepository = userRepository;
	}

	public List<FeedbackFormDto> readAllFromUser(UUID id)
	{
		return FeedbackFormMapper.INSTANCE.entityListToDtoList(feedbackFormRepository.findAllByUserId(id));
	}

	@Override
	public FeedbackFormDto create(FeedbackFormDto dto) throws EntityNotFoundException
	{
		User user = userRepository.getById(dto.getUser().getId());
		dto.setUser(UserMapper.INSTANCE.entityToDto(user));
		return super.create(dto);
	}
}
