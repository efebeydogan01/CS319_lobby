package lobby.pandemica.serviceimpl.mapper;

import lobby.pandemica.db.RequestForm;
import lobby.pandemica.dto.RequestFormDto;
import lobby.pandemica.serviceimpl.mapper.base.BaseMapper;
import lobby.pandemica.serviceimpl.mapper.base.MapConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapConfig.class, componentModel = "spring")
public interface RequestFormMapper extends BaseMapper<RequestForm, RequestFormDto>
{
	RequestFormMapper INSTANCE = Mappers.getMapper(RequestFormMapper.class);
}
