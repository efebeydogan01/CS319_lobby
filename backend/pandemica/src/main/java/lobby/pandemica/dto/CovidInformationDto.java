package lobby.pandemica.dto;

import lobby.pandemica.db.CovidInformation;
import lobby.pandemica.db.Status;
import lobby.pandemica.db.User;
import lobby.pandemica.dto.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CovidInformationDto extends BaseDto<UUID>
{
    private UUID id;
    private String status;
    private String hesCode;
    private Boolean allowedOnCampus;
    private User user;
}