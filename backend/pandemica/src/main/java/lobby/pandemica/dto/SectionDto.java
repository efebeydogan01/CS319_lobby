package lobby.pandemica.dto;

import lobby.pandemica.db.AcademicPersonnel;
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
 * Dto of section entity
 */
public class SectionDto extends BaseDto<UUID>
{
    private UUID id;
    private String courseName;
    private Integer sectionNo;
    private String classroom;
    private AcademicPersonnelDto academicPersonnel;
}