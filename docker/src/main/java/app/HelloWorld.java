package app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by marc on 04/12/15.
 */
@RestController
public class HelloWorld {

    @RequestMapping(path = "/hello")
    public String helloWorld() {
        return "Hello world\n";
    }
}
