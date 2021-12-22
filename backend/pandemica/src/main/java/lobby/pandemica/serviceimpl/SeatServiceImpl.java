package lobby.pandemica.serviceimpl;

import lobby.pandemica.db.*;
import lobby.pandemica.dto.SeatDto;
import lobby.pandemica.repository.SeatRepository;
import lobby.pandemica.service.SeatService;
import lobby.pandemica.serviceimpl.base.BaseServiceImpl;
import lobby.pandemica.serviceimpl.mapper.SeatMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class SeatServiceImpl extends BaseServiceImpl<Seat, SeatDto> implements SeatService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SeatServiceImpl.class);

    private final SeatRepository seatRepository;
    private final SeatMapper seatMapper;

    public SeatServiceImpl(SeatRepository seatRepository, SeatMapper seatMapper) {
        super(seatRepository, SeatMapper.INSTANCE);
        this.seatRepository = seatRepository;
        this.seatMapper = seatMapper;
    }
}
