package lobby.pandemica.dto;

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
//@NoArgsConstructor
public class NeighborDto extends BaseDto<UUID>
{

    //private Section section;
    /*
    private Student neighbor1;
    private Student neighbor2;*/
}
