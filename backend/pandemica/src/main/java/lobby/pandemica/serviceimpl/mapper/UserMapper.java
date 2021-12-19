package lobby.pandemica.serviceimpl.mapper;

import lobby.pandemica.db.User;
import lobby.pandemica.dto.UserDto;
import lobby.pandemica.serviceimpl.mapper.base.BaseMapper;
import lobby.pandemica.serviceimpl.mapper.base.MapConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapConfig.class, componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDto>
{
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
}
