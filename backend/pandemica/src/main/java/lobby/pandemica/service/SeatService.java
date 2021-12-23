package lobby.pandemica.service;

import lobby.pandemica.db.RequestSeat;
import lobby.pandemica.dto.SeatDto;
import lobby.pandemica.service.base.BaseCrudService;

import java.util.UUID;

public interface SeatService extends BaseCrudService<SeatDto>
{
    SeatDto set(RequestSeat requestSeat, UUID studentID);
}