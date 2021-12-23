package lobby.pandemica.db;

import lobby.pandemica.db.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "announcements")

public class Announcement extends BaseEntity {
	//Attributes
	@Column(name = "title")
	private String title;

	@Column(name = "date")
	private String date;

	@Column(name = "announcement_text")
	private String announcementText;
}