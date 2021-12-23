package lobby.pandemica.serviceimpl.mapper;
import lobby.pandemica.db.Announcement;
import lobby.pandemica.dto.AnnouncementDto;
import lobby.pandemica.serviceimpl.mapper.base.BaseMapper;
import lobby.pandemica.serviceimpl.mapper.base.MapConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapConfig.class, componentModel = "spring")
public interface AnnouncementMapper extends BaseMapper<Announcement, AnnouncementDto>
{
    AnnouncementMapper INSTANCE = Mappers.getMapper(AnnouncementMapper.class);
}
