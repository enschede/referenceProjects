package app;

import app.Person;import app.PersonRepository;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.Iterable;import java.lang.Long;import java.lang.String;import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Person> findAll() {
        Iterable<Person> persons = personRepository.findAll();
        return persons;
    }

    @RequestMapping(value = "/persons/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Person findOne(@PathVariable Long id) {
        Person person = personRepository.findOne(id);
        return person;
    }

    @RequestMapping(value = "/persons", method = RequestMethod.POST)
    @ResponseBody
    public Person save(@RequestBody Person person) {
        person.setId(null);
        return personRepository.save(person);
    }

    @RequestMapping(value = "/persons/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Person update(@PathVariable Long id, @RequestBody Person person) {
        person.setId(id);
        return personRepository.save(person);
    }

    @RequestMapping(value = "/persons/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable Long id) {
        personRepository.delete(id);
    }

}
