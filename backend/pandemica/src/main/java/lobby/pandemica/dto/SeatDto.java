package lobby.pandemica.dto;

import lobby.pandemica.dto.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
//@NoArgsConstructor
/**
 * Dto of seat entity
 */
public class SeatDto extends BaseDto<UUID>
{
    private UUID id;
    private SectionDto section;
    private Boolean exists;
    private Integer row;
    private Integer column;
    private StudentDto student;
}