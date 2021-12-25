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
@NoArgsConstructor
/**
 * Dto of neighbor entity
 */
public class NeighborDto extends BaseDto<UUID>
{
    private SectionDto section;
    private StudentDto firstStudent;
    private StudentDto secondStudent;
}
