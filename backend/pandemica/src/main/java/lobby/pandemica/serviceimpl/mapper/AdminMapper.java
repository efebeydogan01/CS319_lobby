package lobby.pandemica.serviceimpl.mapper;

import lobby.pandemica.db.Admin;
import lobby.pandemica.dto.AdminDto;
import lobby.pandemica.serviceimpl.mapper.base.BaseMapper;
import lobby.pandemica.serviceimpl.mapper.base.MapConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapConfig.class, componentModel = "spring")
public interface AdminMapper extends BaseMapper<Admin, AdminDto>
{
    AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class);
}
