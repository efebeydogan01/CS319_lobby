package lobby.pandemica.db;

import lobby.pandemica.db.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Represents the feedback form created by the user User
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "feedback_form")
public class FeedbackForm extends BaseEntity
{
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@Column(name = "rating")
	@Min(0)
	@Max(10)
	private Integer rating;
	
	@Column(name = "title",columnDefinition = "TEXT")
	private String title;

	@Column(name = "feedback", columnDefinition = "TEXT")
	private String feedback;
}
