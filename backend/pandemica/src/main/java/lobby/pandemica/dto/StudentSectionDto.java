package lobby.pandemica.dto;

import lobby.pandemica.db.Section;
import lobby.pandemica.db.Student;
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
 * Dto of studentSection entity
 */
public class StudentSectionDto extends BaseDto<UUID>
{
    private UUID id;
    private StudentDto student;
    private SectionDto section;
}
