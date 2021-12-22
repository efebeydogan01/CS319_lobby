package lobby.pandemica.serviceimpl.mapper;

import lobby.pandemica.db.AcademicPersonnel;
import lobby.pandemica.dto.AcademicPersonnelDto;
import lobby.pandemica.serviceimpl.mapper.base.BaseMapper;
import lobby.pandemica.serviceimpl.mapper.base.MapConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapConfig.class, componentModel = "spring")
public interface AcademicPersonnelMapper extends BaseMapper<AcademicPersonnel, AcademicPersonnelDto>
{
    AcademicPersonnelMapper INSTANCE = Mappers.getMapper(AcademicPersonnelMapper.class);
}
