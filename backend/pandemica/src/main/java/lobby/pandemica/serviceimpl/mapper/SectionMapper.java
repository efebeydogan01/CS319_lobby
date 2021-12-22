package lobby.pandemica.serviceimpl.mapper;

import lobby.pandemica.db.Section;
import lobby.pandemica.dto.SectionDto;
import lobby.pandemica.serviceimpl.mapper.base.BaseMapper;
import lobby.pandemica.serviceimpl.mapper.base.MapConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapConfig.class, componentModel = "spring")
public interface SectionMapper extends BaseMapper<Section, SectionDto>
{
    SectionMapper INSTANCE = Mappers.getMapper(SectionMapper.class);
}
