package lobby.pandemica.db;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role
{
	public enum Roles{
		ADMIN,
		MEDICAL_EMPLOYEE,
		STUDENT,
		ACADEMIC_PERSONNEL
	}
	public Roles role;
}
