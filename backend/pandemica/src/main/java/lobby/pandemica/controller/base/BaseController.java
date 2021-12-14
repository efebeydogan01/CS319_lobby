package lobby.pandemica.controller.base;

import lobby.pandemica.dto.base.BaseDto;
import lobby.pandemica.service.base.BaseCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public abstract class BaseController<D extends BaseDto<UUID>>
{
	private final BaseCrudService<D> baseCrudService;

	public BaseController(BaseCrudService<D> baseCrudService)
	{
		this.baseCrudService = baseCrudService;
	}

	@PostMapping(value = "create")
	public ResponseEntity<D> create(@RequestBody D dto) {
		return new ResponseEntity<>(baseCrudService.create(dto), HttpStatus.OK);
	}
}
