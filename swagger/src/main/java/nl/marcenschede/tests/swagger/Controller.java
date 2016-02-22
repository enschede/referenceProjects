package nl.marcenschede.tests.swagger;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @RequestMapping(value = "/greater/{name}", method = RequestMethod.GET)
    public String greeter(
            @PathVariable("name") String name,
            @RequestParam(value = "achternaam", required = false) String achternaam) {
        return "Hello " + name + " " + achternaam;
    }

    @ApiOperation(value = "getGreeting",
            notes = "Dit is een note op een operation",
            response = Greeting.class,
            nickname = "theEasyOne")
    @RequestMapping(value = "/greeting/{name}", method = RequestMethod.GET)
    @ApiResponses( value = {
            @ApiResponse(code = 404, message = "Er is geen gebruiker met deze mooie naam")
    })
    @ResponseBody
    public Greeting greeting(
            @ApiParam(value = "Naam van de gebruiker") @PathVariable("name") String name,
            @ApiParam(value = "Achternaam van de gebruiker") @RequestParam(value = "achternaam", required = true) String achternaam) {
        return new Greeting(10L, name + " " + achternaam);
    }

    @ApiModel(value = "Greeting", description = "Beschijving van greeting")
    class Greeting {

        @ApiModelProperty(value = "Id het element", required = true, readOnly = true)
        private final long id;
        @ApiModelProperty(value = "Content het element", required = true)
        private final String content;

        public Greeting(long id, String content) {
            this.id = id;
            this.content = content;
        }

        public long getId() {
            return id;
        }

        public String getContent() {
            return content;
        }
    }


}
