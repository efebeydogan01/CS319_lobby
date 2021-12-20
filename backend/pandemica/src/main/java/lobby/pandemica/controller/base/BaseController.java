package lobby.pandemica.controller.base;

import lobby.pandemica.db.CustomId;
import lobby.pandemica.dto.base.BaseDto;
import lobby.pandemica.dto.base.RestResponse;
import lobby.pandemica.service.base.BaseCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@CrossOrigin()
public abstract class BaseController<D extends BaseDto<UUID>>
{
	private final BaseCrudService<D> baseCrudService;

	public BaseController(BaseCrudService<D> baseCrudService)
	{
		this.baseCrudService = baseCrudService;
	}

	@PostMapping(value = "create")
	public ResponseEntity<RestResponse<D>> create(@RequestBody D dto) {
		try
		{
			return new ResponseEntity<>(
					new RestResponse<>(baseCrudService.create(dto), "Create", "Creating Entity was successful."),
					HttpStatus.OK
			);
		}
		catch (EntityNotFoundException e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Create",
					"Creating Entity was unsuccessful due to an error with the entity given."),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Create", "There was an unexpected error."),
					HttpStatus.EXPECTATION_FAILED);
		}
	}

	@PostMapping(value = "createAll")
	public ResponseEntity<RestResponse<List<D>>> createAll(@RequestBody List<D> dtoList) {
		try
		{
			return new ResponseEntity<>(
					new RestResponse<>(baseCrudService.createAll(dtoList),"Create All", "Create All was successful."),
					HttpStatus.OK);
		}
		catch (EntityNotFoundException e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Create All",
					"Creating entities was unsuccessful due to an error with the entities given."),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Create All", "There was an unexpected error."),
					HttpStatus.EXPECTATION_FAILED);
		}
	}

	@GetMapping(value = "read")
	public ResponseEntity<RestResponse<D>> read(@RequestBody CustomId id)
	{
		try
		{
			return new ResponseEntity<>(new RestResponse<>(baseCrudService.read(id.getId()), "Read",
					"Reading an entity was successful."),
					HttpStatus.OK);
		}
		catch (EntityNotFoundException e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Read",
					"Reading entities was unsuccessful due to an error with the entities given."),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Read","There was an unexpected error."),
					HttpStatus.EXPECTATION_FAILED);
		}
	}

	@GetMapping(value = "readAll")
	public ResponseEntity<List<D>> readAll()
	{
		try
		{
			return new ResponseEntity<>(baseCrudService.read(), HttpStatus.OK);
		}
		catch (EntityNotFoundException e)
		{
			return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}

	@PutMapping(value = "update")
	public ResponseEntity<RestResponse<D>> update(@RequestBody D dto) {
		try
		{
			return new ResponseEntity<>(new RestResponse<>(baseCrudService.update(dto),"Update", "Updating an entity was successful."),
					HttpStatus.OK);
		}
		catch (EntityNotFoundException e)
		{
			return new ResponseEntity<>(new RestResponse<>(null,"Update",
					"Updating an entity was unsuccessful due to an error with the entity given."),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(new RestResponse<>(null,"Update",
					"There was an unexpected error."), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@PutMapping(value = "updateAll")
	public ResponseEntity<RestResponse<List<D>>> updateAll(@RequestBody List<D> dtoList) {
		try
		{
			return new ResponseEntity<>(new RestResponse<>(baseCrudService.updateAll(dtoList),"Update All",
					"Updating entities was successful."),
					HttpStatus.OK);
		}
		catch (EntityNotFoundException e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Update All",
					"Updating entities was unsuccessful due to an error with the entities given."),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Update All",
					"There was an unexpected error."),
					HttpStatus.EXPECTATION_FAILED);
		}
	}

	@DeleteMapping(value = "delete/{id}")
	public ResponseEntity<RestResponse<D>> delete(@PathVariable String id)
	{
		try
		{
			return new ResponseEntity<>(new RestResponse<>(baseCrudService.delete(UUID.fromString(id)),"Delete",
					"Deleting an entity was successful."),
					HttpStatus.OK);
		}
		catch (EntityNotFoundException e)
		{
			return new ResponseEntity<>(new RestResponse<>(null,
					"Deleting an entity was unsuccessful due to an error with the entity given."),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(new RestResponse<>(null,
					"There was an unexpected error."),
					HttpStatus.EXPECTATION_FAILED);
		}
	}

	@DeleteMapping(value = "deleteAll")
	public ResponseEntity<RestResponse<List<D>>> deleteAll(@RequestBody List<String> idList)
	{
		try
		{
			return new ResponseEntity<>(new RestResponse<>(baseCrudService.deleteAll(idList),"Delete from list",
					"Deleting an entity list was successful."),
					HttpStatus.OK);
		}
		catch (EntityNotFoundException e)
		{
			return new ResponseEntity<>(new RestResponse<>(null,
					"Deleting an entity list was unsuccessful due to an error with the entity given."),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(new RestResponse<>(null,
					"There was an unexpected error."),
					HttpStatus.EXPECTATION_FAILED);
		}
	}
}
