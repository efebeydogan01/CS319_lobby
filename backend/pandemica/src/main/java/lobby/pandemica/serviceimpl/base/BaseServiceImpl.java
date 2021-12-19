package lobby.pandemica.serviceimpl.base;

import lobby.pandemica.db.base.BaseEntity;
import lobby.pandemica.dto.base.BaseDto;
import lobby.pandemica.repository.base.BaseRepository;
import lobby.pandemica.service.base.BaseCrudService;
import lobby.pandemica.serviceimpl.mapper.base.BaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BaseServiceImpl<E extends BaseEntity, D extends BaseDto<UUID>> implements BaseCrudService<D>
{
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseServiceImpl.class);

	protected final BaseRepository<E, UUID> baseRepository;

	private final BaseMapper<E, D> baseMapper;

	public BaseServiceImpl(BaseRepository<E, UUID> baseRepository, BaseMapper<E, D> baseMapper) {
		this.baseRepository = baseRepository;
		this.baseMapper = baseMapper;
	}

	@Override
	public D create(D dto)
	{
		E entity = baseMapper.dtoToEntity(dto);
		return baseMapper.entityToDto(baseRepository.save(entity));
	}

	@Override
	public List<D> createAll(List<D> dtoList)
	{
		List<E> list = new ArrayList<>();
		for (D dto: dtoList)
		{
			E entity = baseMapper.dtoToEntity(dto);
			list.add(entity);
		}
		return baseMapper.entityListToDtoList(baseRepository.saveAll(list));
	}

	@Override
	public D read(UUID id)
	{
		return baseMapper.entityToDto(baseRepository.getById(id));
	}

	@Override
	public List<D> read()
	{
		return baseMapper.entityListToDtoList(baseRepository.findAll());
	}

	@Override
	public D update(D dto)
	{
		return null;
	}

	@Override
	public List<D> updateAll(List<D> dtoList)
	{
		return null;
	}

	@Override
	public D delete(UUID id)
	{
		return null;
	}

	@Override
	public List<D> deleteAll(List<UUID> idList)
	{
		return null;
	}

	@Override
	public boolean existsById(UUID id)
	{
		return false;
	}

	@Override
	public List<D> read(List<UUID> idlist) throws Exception
	{
		return null;
	}
}
