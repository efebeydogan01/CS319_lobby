package lobby.pandemica.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lobby.pandemica.db.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

/**
 * Represents a notification.
 * This notifications receiver can either be a role of users (see receivers), or a custom student when one of their neighbors
 * gets infected with COVID (see receiver).
 */
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

	@Column(name = "receivers")
	private String receivers;

	@Column(name = "title", columnDefinition = "TEXT")
	private String title;

	@Column(name = "message", columnDefinition = "TEXT")
	private String message;

	@SuppressWarnings("squid:S3437")
	@JsonIgnore
	@Column(name = "createdOn", updatable = false)
	private Instant createdOn;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "receiver_id", referencedColumnName = "id")
	private User receiver;
}
