package lobby.pandemica.controller;

import lobby.pandemica.controller.base.BaseController;
import lobby.pandemica.dto.VaccineInformationDto;
import lobby.pandemica.dto.base.RestResponse;
import lobby.pandemica.service.VaccineInformationService;
import lobby.pandemica.service.base.BaseCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("vaccine")
public class VaccineInformationController extends BaseController<VaccineInformationDto>
{
	private final VaccineInformationService vaccineInformationService;

	public VaccineInformationController(BaseCrudService<VaccineInformationDto> baseCrudService, VaccineInformationService vaccineInformationService)
	{
		super(baseCrudService);
		this.vaccineInformationService = vaccineInformationService;
	}

	@PostMapping("uploadFile/{id}, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }")
	public ResponseEntity<RestResponse<List<String>>> uploadVacFile(@RequestPart("file") MultipartFile multipartFile, @PathVariable String id)
	{
		try
		{
			return new ResponseEntity<>(new RestResponse<>(vaccineInformationService.upload(multipartFile, UUID.fromString(id)), "Get",
					"Getting an entity was successful."),
					HttpStatus.OK);
		}
		catch (EntityNotFoundException e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Get",
					"Getting entity was unsuccessful due to an error with the entities given."),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Get","There was an unexpected error."),
					HttpStatus.EXPECTATION_FAILED);
		}
	}
}
