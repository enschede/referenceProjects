package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping("/json")
    @ResponseBody
    public Greeting greeting() {
        return new Greeting(10L, "Hallo!");
    }

    @RequestMapping("/hello")
    @ResponseBody
    public Person hello() {

        Person person = new Person("Test person");
        personRepository.save(person);

        return person;
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
