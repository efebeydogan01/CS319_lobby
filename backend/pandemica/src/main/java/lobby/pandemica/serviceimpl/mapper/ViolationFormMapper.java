package lobby.pandemica.serviceimpl.mapper;

import lobby.pandemica.db.ViolationReport;
import lobby.pandemica.dto.ViolationReportDto;
import lobby.pandemica.serviceimpl.mapper.base.BaseMapper;
import lobby.pandemica.serviceimpl.mapper.base.MapConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapConfig.class, componentModel = "spring")
public interface ViolationFormMapper extends BaseMapper<ViolationReport, ViolationReportDto>
{
	ViolationFormMapper INSTANCE = Mappers.getMapper(ViolationFormMapper.class);
}
