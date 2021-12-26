package lobby.pandemica.service;

import lobby.pandemica.db.RequestSeatingPlan;
import lobby.pandemica.db.Section;
import lobby.pandemica.db.TempDetails;
import lobby.pandemica.dto.SeatDto;
import lobby.pandemica.dto.SectionDto;
import lobby.pandemica.dto.UserDto;
import lobby.pandemica.service.base.BaseCrudService;

import java.util.List;
import java.util.UUID;

public interface SectionService extends BaseCrudService<SectionDto>
{
    List<SeatDto> getSeatingPlan(RequestSeatingPlan requestSeatingPlan);
    List<Section> getSections(UUID userId);
}