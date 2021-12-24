package lobby.pandemica.serviceimpl.mapper;

import lobby.pandemica.db.FeedbackForm;
import lobby.pandemica.dto.FeedbackFormDto;
import lobby.pandemica.serviceimpl.mapper.base.BaseMapper;
import lobby.pandemica.serviceimpl.mapper.base.MapConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapConfig.class, componentModel = "spring")
public interface FeedbackFormMapper extends BaseMapper<FeedbackForm, FeedbackFormDto>
{
	FeedbackFormMapper INSTANCE = Mappers.getMapper(FeedbackFormMapper.class);
}
