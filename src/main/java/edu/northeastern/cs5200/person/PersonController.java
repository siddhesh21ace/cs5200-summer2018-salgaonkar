package edu.northeastern.cs5200.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/api/person")
    public Person createPerson(@RequestBody Person person) {
        return personService.createPerson(person);
    }

    @GetMapping("/api/person")
    public List<Person> findAllPersons() {
        return personService.findAllPersons();
    }

    @GetMapping("/api/person/{personId}")
    public Person findPersonById(@PathVariable("personId") int personId) throws Exception {
        return personService.findPersonById(personId);
    }

    @PutMapping("/api/person/{personId}")
    public Person updatePerson(@PathVariable("personId") int personId,
                               @RequestBody Person newPerson) throws Exception {
        return personService.updatePerson(personId, newPerson);
    }

    @DeleteMapping("/api/person/{personId}")
    public void deletePerson(@PathVariable("personId") int id) {
        personService.deletePerson(id);
    }

}
