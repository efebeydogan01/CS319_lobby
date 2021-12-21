package lobby.pandemica.dto;

import lobby.pandemica.db.CovidInformation;
import lobby.pandemica.db.User;
import lobby.pandemica.dto.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestResultDto extends BaseDto<UUID>
{
    private CovidInformationDto covidInformation;
    private Date testDate;
    private String testPlace;
    private Boolean result;
}