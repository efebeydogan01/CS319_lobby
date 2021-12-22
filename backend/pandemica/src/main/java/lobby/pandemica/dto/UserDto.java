package lobby.pandemica.dto;

import lobby.pandemica.db.CovidInformation;

import lobby.pandemica.db.Student;
import lobby.pandemica.dto.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends BaseDto<UUID>
{
	private UUID id;
	private String name;
	private String password;
	private Integer bilkentId;
	private Date dateOfBirth;
	private String phoneNumber;
	private Integer age;
	private String role;
}
