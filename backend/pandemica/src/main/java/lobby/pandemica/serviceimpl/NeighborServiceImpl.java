package lobby.pandemica.serviceimpl;

import lobby.pandemica.db.Neighbor;
import lobby.pandemica.dto.NeighborDto;
import lobby.pandemica.repository.NeighborRepository;
import lobby.pandemica.service.NeighborService;
import lobby.pandemica.serviceimpl.base.BaseServiceImpl;
import lobby.pandemica.serviceimpl.mapper.NeighborMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NeighborServiceImpl extends BaseServiceImpl<Neighbor, NeighborDto> implements NeighborService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(NeighborServiceImpl.class);

    private final NeighborRepository neighborRepository;

    public NeighborServiceImpl(NeighborRepository neighborRepository) {
        super(neighborRepository, NeighborMapper.INSTANCE);
        this.neighborRepository = neighborRepository;
    }
}
