package edu.northeastern.cs5200.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    Person createPerson(Person person) {
        return personRepository.save(person);
    }

    List<Person> findAllPersons() {
        return (List<Person>) personRepository.findAll();
    }

    Person findPersonById(int personId) throws Exception {
        Optional<Person> data = personRepository.findById(personId);
        if (data.isPresent()) {
            return data.get();
        }
        throw new Exception("No person found with id = " + personId);
    }

    Person updatePerson(int personId, Person newPerson) throws Exception {
        Optional<Person> data = personRepository.findById(personId);
        if (data.isPresent()) {
            Person person = data.get();
            person.setUsername(newPerson.getUsername());
            person.setFirstName(newPerson.getFirstName());
            person.setLastName(newPerson.getLastName());
            person.setEmail(newPerson.getEmail());
            person.setDob(newPerson.getDob());
            return personRepository.save(person);
        }
        throw new Exception("No person found with id = " + personId);
    }

    void deletePerson(int id) {
        personRepository.deleteById(id);
    }

}
