package lobby.pandemica.serviceimpl.mapper;

import lobby.pandemica.db.Seat;
import lobby.pandemica.dto.SeatDto;
import lobby.pandemica.serviceimpl.mapper.base.BaseMapper;
import lobby.pandemica.serviceimpl.mapper.base.MapConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapConfig.class, componentModel = "spring")
public interface SeatMapper extends BaseMapper<Seat, SeatDto>
{
    SeatMapper INSTANCE = Mappers.getMapper(SeatMapper.class);
}
