package lobby.pandemica.controller;

import lobby.pandemica.controller.base.BaseController;
import lobby.pandemica.dto.TestResultDto;
import lobby.pandemica.service.TestResultService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test_result")
public class TestResultController extends BaseController<TestResultDto> {
    private final TestResultService testResultService;

    public TestResultController(TestResultService testResultService) {
        super(testResultService);
        this.testResultService = testResultService;
    }
}
