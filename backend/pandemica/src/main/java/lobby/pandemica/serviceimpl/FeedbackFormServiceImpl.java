package lobby.pandemica.serviceimpl;

import lobby.pandemica.db.FeedbackForm;
import lobby.pandemica.dto.FeedbackFormDto;
import lobby.pandemica.repository.FeedbackFormRepository;
import lobby.pandemica.repository.base.BaseRepository;
import lobby.pandemica.service.FeedbackFormService;
import lobby.pandemica.serviceimpl.base.BaseServiceImpl;
import lobby.pandemica.serviceimpl.mapper.FeedbackFormMapper;
import lobby.pandemica.serviceimpl.mapper.base.BaseMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FeedbackFormServiceImpl extends BaseServiceImpl<FeedbackForm, FeedbackFormDto> implements FeedbackFormService
{
	private final FeedbackFormRepository feedbackFormRepository;

	public FeedbackFormServiceImpl(BaseRepository<FeedbackForm, UUID> baseRepository, BaseMapper<FeedbackForm, FeedbackFormDto> baseMapper, FeedbackFormRepository feedbackFormRepository)
	{
		super(baseRepository, baseMapper);
		this.feedbackFormRepository = feedbackFormRepository;
	}

	@Override
	public List<FeedbackFormDto> readAllFromUser(UUID id)
	{
		return FeedbackFormMapper.INSTANCE.entityListToDtoList(feedbackFormRepository.findAllByUserId(id));
	}
}
