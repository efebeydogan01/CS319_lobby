package lobby.pandemica.service.base;

import lobby.pandemica.dto.base.BaseDto;

import java.util.List;
import java.util.UUID;

public interface BaseCrudService<D extends BaseDto<UUID>>
{
	D create(D dto);

	List<D> createAll(List<D> dto);

	D read(UUID id);

	List<D> read();

	D update(D dto);

	List<D> updateAll(List<D> dtoList);

	D delete(UUID id);

	List<D> deleteAll(List<String> idList);

	boolean existsById(UUID id);
}
