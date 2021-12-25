package lobby.pandemica.serviceimpl.mapper;

import lobby.pandemica.db.VaccineInformation;
import lobby.pandemica.dto.VaccineInformationDto;
import lobby.pandemica.serviceimpl.mapper.base.BaseMapper;
import lobby.pandemica.serviceimpl.mapper.base.MapConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapConfig.class, componentModel = "spring")
public interface VaccineInformationMapper extends BaseMapper<VaccineInformation, VaccineInformationDto>
{
    VaccineInformationMapper INSTANCE = Mappers.getMapper(VaccineInformationMapper.class);
}
