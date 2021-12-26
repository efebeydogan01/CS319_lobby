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
 * Dto of feedback form entity
 */
public class FeedbackFormDto extends BaseDto<UUID>
{
	private UserDto user;
	private Integer rating;
	private String title;
	private String feedback;
}
