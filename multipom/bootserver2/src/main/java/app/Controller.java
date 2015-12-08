package app;

import multipom.generics.Greeter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @RequestMapping("/json")
    @ResponseBody
    public Greeter greeting() {
        return new Greeter(10L, "Hallo!");
    }

}
