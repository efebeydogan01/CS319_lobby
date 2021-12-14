package lobby.pandemica.dto.base;

import java.io.Serializable;

public class BaseDto<T> implements Serializable
{
	private T id;

	public T getId() {
		return this.id;
	}
	public void setId(T id) {
		this.id = id;
	}
}
