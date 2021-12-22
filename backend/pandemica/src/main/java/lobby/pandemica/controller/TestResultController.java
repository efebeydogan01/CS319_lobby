package lobby.pandemica.controller;

import lobby.pandemica.controller.base.BaseController;
import lobby.pandemica.dto.TestResultDto;
import lobby.pandemica.dto.base.RestResponse;
import lobby.pandemica.service.TestResultService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("test_result")
public class TestResultController extends BaseController<TestResultDto> {
    private final TestResultService testResultService;

    public TestResultController(TestResultService testResultService) {
        super(testResultService);
        this.testResultService = testResultService;
    }

    @GetMapping("getTestResults/{id}")
    public ResponseEntity<RestResponse<List<TestResultDto>>> getTestResults(@PathVariable String id)
    {
        try
        {
            return new ResponseEntity<>(
                    new RestResponse<>(testResultService.getTestResults(UUID.fromString(id)),"Getting Test Results",
                            "Getting Test Results was successful."),
                    HttpStatus.OK);
        }
        catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>(new RestResponse<>(null, "Getting Test Results",
                    "Getting Test Results was unsuccessful due to an error with the entities given."),
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new RestResponse<>(null, "Getting Risk Status",
                    "There was an unexpected error."),
                    HttpStatus.EXPECTATION_FAILED);
        }
    }
}
