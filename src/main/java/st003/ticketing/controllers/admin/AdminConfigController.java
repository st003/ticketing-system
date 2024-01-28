package st003.ticketing.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminConfigController {

    @RequestMapping("/admin/config")
    public String getAdminConfig() {
        return "admin/config";
    }
}
