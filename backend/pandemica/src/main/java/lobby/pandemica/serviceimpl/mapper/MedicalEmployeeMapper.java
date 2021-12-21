package lobby.pandemica.serviceimpl.mapper;

import lobby.pandemica.db.MedicalEmployee;
import lobby.pandemica.dto.MedicalEmployeeDto;
import lobby.pandemica.serviceimpl.mapper.base.BaseMapper;
import lobby.pandemica.serviceimpl.mapper.base.MapConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapConfig.class, componentModel = "spring")
public interface MedicalEmployeeMapper extends BaseMapper<MedicalEmployee, MedicalEmployeeDto>
{
    MedicalEmployeeMapper INSTANCE = Mappers.getMapper(MedicalEmployeeMapper.class);
}
