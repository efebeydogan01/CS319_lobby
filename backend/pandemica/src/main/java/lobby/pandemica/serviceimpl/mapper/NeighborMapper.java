package lobby.pandemica.serviceimpl.mapper;

import lobby.pandemica.db.Neighbor;
import lobby.pandemica.dto.NeighborDto;
import lobby.pandemica.serviceimpl.mapper.base.BaseMapper;
import lobby.pandemica.serviceimpl.mapper.base.MapConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapConfig.class, componentModel = "spring")
public interface NeighborMapper extends BaseMapper<Neighbor, NeighborDto>
{
    NeighborMapper INSTANCE = Mappers.getMapper(NeighborMapper.class);
}
