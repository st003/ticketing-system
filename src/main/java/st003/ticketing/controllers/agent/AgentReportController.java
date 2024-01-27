package st003.ticketing.controllers.agent;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AgentReportController {

    @RequestMapping("/agent/report")
    public String getAgentReport() {
        return "agent/report";
    }
}
