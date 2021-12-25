package lobby.pandemica.db;

import lobby.pandemica.db.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Represents an announcement made by an admin.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "announcements")
/**
 * Entity class representing an announcement, made by admins, shown to all the users
 */
public class Announcement extends BaseEntity {
	//Attributes
	@Column(name = "title")
	private String title;

	@Column(name = "date")
	private String date;

	@Column(name = "announcement_text", columnDefinition = "TEXT")
	private String announcementText;
}