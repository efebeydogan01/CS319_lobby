package lobby.pandemica.controller;

import lobby.pandemica.controller.base.BaseController;
import lobby.pandemica.db.GeneralInfo;
import lobby.pandemica.dto.AnnouncementDto;
import lobby.pandemica.dto.base.RestResponse;
import lobby.pandemica.service.AnnouncementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

/**
 * Controller for Announcement, extends from Base Controller
 */
@RestController
@RequestMapping("announcement")
public class AnnouncementController extends BaseController<AnnouncementDto> {
	private final AnnouncementService announcementService;

	public AnnouncementController(AnnouncementService announcementService) {
		super(announcementService);
		this.announcementService = announcementService;
	}

	/**
	 * Reads the covid information of the whole university to serve in the general information.
	 * @return the total statistics of the university
	 */
	@GetMapping("readGeneralInfo")
	public ResponseEntity<RestResponse<GeneralInfo>> read()
	{
		try
		{
			return new ResponseEntity<>(new RestResponse<>(announcementService.readGeneralInfo(), "Get",
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