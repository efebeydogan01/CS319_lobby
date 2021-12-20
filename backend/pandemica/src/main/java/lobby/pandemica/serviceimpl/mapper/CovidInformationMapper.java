package lobby.pandemica.serviceimpl.mapper;

import lobby.pandemica.db.CovidInformation;
import lobby.pandemica.dto.CovidInformationDto;
import lobby.pandemica.serviceimpl.mapper.base.BaseMapper;
import lobby.pandemica.serviceimpl.mapper.base.MapConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapConfig.class, componentModel = "spring")
public interface CovidInformationMapper extends BaseMapper<CovidInformation, CovidInformationDto>
{
    CovidInformationMapper INSTANCE = Mappers.getMapper(CovidInformationMapper.class);
}
