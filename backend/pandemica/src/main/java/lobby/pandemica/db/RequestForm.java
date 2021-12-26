package lobby.pandemica.db;

import lobby.pandemica.db.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Represents the request form created by a user.
 * This class holds a reference to the user who created this form.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "request_form")
/**
 * Entity class of requestForm sent by users, includes the sender and text
 */
public class RequestForm extends BaseEntity
{
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@Column(name = "title",columnDefinition = "TEXT")
	private String title;

	@Column(name = "request", columnDefinition = "TEXT")
	private String request;
}
