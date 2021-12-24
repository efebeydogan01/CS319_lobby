package lobby.pandemica.serviceimpl.mapper;

import lobby.pandemica.db.Notification;
import lobby.pandemica.dto.NotificationDto;
import lobby.pandemica.serviceimpl.mapper.base.BaseMapper;
import lobby.pandemica.serviceimpl.mapper.base.MapConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapConfig.class, componentModel = "spring")
public interface NotificationMapper extends BaseMapper<Notification, NotificationDto>
{
	NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);
}
