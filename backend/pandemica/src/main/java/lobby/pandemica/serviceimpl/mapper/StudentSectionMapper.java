package lobby.pandemica.serviceimpl.mapper;

import lobby.pandemica.db.Neighbor;
import lobby.pandemica.db.StudentSection;
import lobby.pandemica.dto.NeighborDto;
import lobby.pandemica.dto.StudentSectionDto;
import lobby.pandemica.serviceimpl.mapper.base.BaseMapper;
import lobby.pandemica.serviceimpl.mapper.base.MapConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapConfig.class, componentModel = "spring")
public interface StudentSectionMapper extends BaseMapper<StudentSection, StudentSectionDto>
{
    StudentSectionMapper INSTANCE = Mappers.getMapper(StudentSectionMapper.class);
}
