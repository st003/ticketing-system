package st003.ticketing.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminAgentsController {

    @RequestMapping("/admin/agents")
    public String getAdminAgents() {
        return "admin/agents";
    }
}
