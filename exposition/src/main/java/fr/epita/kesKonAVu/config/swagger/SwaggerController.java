package fr.epita.kesKonAVu.config.swagger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SwaggerController {
    /**
     * Default constructor
     */
    public SwaggerController() {
        super();
    }

    @RequestMapping(value = "/")
    public String index() {
        return "redirect:swagger-ui/";
    }

}
