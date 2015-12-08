package app.presentation;

import app.application.VerwerkVergunningProcessor;
import app.domain.VergunningAanvraag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private VerwerkVergunningProcessor verwerkVergunningProcessor;

    public Controller() {
    }

    public Controller(VerwerkVergunningProcessor verwerkVergunningProcessor) {
        this.verwerkVergunningProcessor = verwerkVergunningProcessor;
    }
    
    @RequestMapping("/greeting")
    @ResponseBody
    public Greeting greeting() {
        return new Greeting(10L, "Hallo!");
    }

    @RequestMapping(value = "/verwerkvergunnningaanvraag", method = RequestMethod.POST)
    @ResponseBody
    public String verwerkVergunningAanvraag(@RequestBody VergunningAanvraag aanvraag) {
        verwerkVergunningProcessor.verwerk(aanvraag);
        
        return "";
    }


    
    
    class Greeting {

        private final long id;
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
