package edu.northeastern.cs5200.person;

import edu.northeastern.cs5200.pet.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

import static edu.northeastern.cs5200.util.Constants.PERSON_ID;

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
    public Person findPersonById(@PathVariable("personId") Long personId) throws Exception {
        return personService.findPersonById(personId);
    }

    @PutMapping("/api/person/{personId}")
    public Person updatePerson(@PathVariable("personId") Long personId,
                               @RequestBody Person newPerson) throws Exception {
        return personService.updatePerson(personId, newPerson);
    }

    @DeleteMapping("/api/person/{personId}")
    public void deletePerson(@PathVariable("personId") Long id) {
        personService.deletePerson(id);
    }

    @PostMapping("/api/person/{personId}/pet/{petId}")
    public Person addPet(@PathVariable("personId") Long personId,
                         @PathVariable("petId") Long petId) {
        return personService.addPet(personId, petId);
    }

    @GetMapping("/api/person/{personId}/pet")
    public Iterable<Pet> findPets(@PathVariable("personId") Long personId) {
        return personService.findPets(personId);
    }

    @GetMapping(value = "/api/person", params = "username")
    public Person findUserByUsername(@RequestParam String username) throws Exception {
        return personService.findPersonByUsername(username);
    }

//    @PostMapping("/api/register")
//    public Person register(@RequestBody Person person, HttpSession session) throws Exception {
//        if (personService.findPersonByUsername(person.getUsername()) != null) {
//            throw new Exception("Username already exists.");
//        } else {
//            session.setAttribute(PERSON_ID, person.getId());
//            return personService.createPerson(person);
//        }
//    }

//    @PutMapping("/api/profile/{userId}")
//    public User updateProfile(@PathVariable("userId") int userId, @RequestBody User newUser) throws Exception {
//        return userService.updateUser(userId, newUser);
//    }

    @PostMapping("/api/login")
    public Person login(@RequestBody Person person, HttpSession session) throws Exception {
        Person foundPerson = personService.findPersonByCredentials(person);
        if (foundPerson != null) {
            session.setAttribute(PERSON_ID, foundPerson.getId());
            return foundPerson;
        }
        throw new Exception("No user found with those credentials");
    }

    @GetMapping("/api/person/current")
    public Person getCurrentPerson(HttpSession session) {
        Person person = null;
        Long personId = (Long) session.getAttribute(PERSON_ID);
        if (personId != null) {
            person = personService.findPersonById(personId);
            if (person == null) {
                throw new IllegalArgumentException("User not found!");
            }
        }
        return person;
    }

    @GetMapping("/api/isLoggedIn")
    public Boolean isLoggedIn(HttpSession session) {
        Long personId = null;
        if (session.getAttribute(PERSON_ID) != null)
            personId = (Long) session.getAttribute(PERSON_ID);
        return personId != null;
    }

    @PostMapping("/api/logout")
    public void logout(HttpSession session) {
        session.removeAttribute(PERSON_ID);
        session.invalidate();
    }

    @GetMapping("/api/admin/confirm")
    public Person isAdmin(HttpSession session) {
        Person person = getCurrentPerson(session);
        if (person != null && person.getRole().equals("Admin")) {
            return person;
        }
        return null;
    }
}
