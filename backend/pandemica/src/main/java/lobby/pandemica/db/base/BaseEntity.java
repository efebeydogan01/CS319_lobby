package lobby.pandemica.db.base;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;
import org.hibernate.id.UUIDGenerator;

/**
 * Represents the base entity of any entity in the database.
 * This class is not created as a table, but it is used as a template for the other entities to use.
 */
@MappedSuperclass
public abstract class BaseEntity
{
	@Id
	@GenericGenerator(name = "pandemica-identity", strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator = "pandemica-identity")
	private UUID id;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
}
