package lobby.pandemica.controller;

import lobby.pandemica.controller.base.BaseController;
import lobby.pandemica.dto.AdminDto;
import lobby.pandemica.service.AdminService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AdminController extends BaseController<AdminDto> {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        super(adminService);
        this.adminService = adminService;
    }
}
