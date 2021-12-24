package lobby.pandemica.db;

import lobby.pandemica.db.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notifications")
public class Notification extends BaseEntity
{
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id",referencedColumnName = "id")
	private User user;

	@Column(name = "receivers", nullable = false)
	private String receivers;

	@Column(name = "title", columnDefinition = "TEXT")
	private String title;

	@Column(name = "message", columnDefinition = "TEXT")
	private String message;
}
