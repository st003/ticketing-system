package st003.ticketing.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminSettingsController {

    @RequestMapping("/admin/settings")
    public String getAdminSettings() {
        return "admin/settings";
    }
}
