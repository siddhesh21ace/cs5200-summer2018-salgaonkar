package edu.northeastern.cs5200.person;

import edu.northeastern.cs5200.pet.Pet;
import edu.northeastern.cs5200.pet.PetRepository;
import edu.northeastern.cs5200.pokemon.Pokemon;
import edu.northeastern.cs5200.pokemon.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final PetRepository petRepository;
    private final PokemonRepository pokemonRepository;

    @Autowired
    public PersonService(PersonRepository personRepository,
                         PetRepository petRepository,
                         PokemonRepository pokemonRepository) {
        this.personRepository = personRepository;
        this.petRepository = petRepository;
        this.pokemonRepository = pokemonRepository;
    }

    Person createPerson(Person person) {
        return personRepository.save(person);
    }

    List<Person> findAllPersons() {
        return (List<Person>) personRepository.findAll();
    }

    Person findPersonById(Long personId) {
        Optional<Person> data = personRepository.findById(personId);
        return data.orElse(null);
    }

    Person updatePerson(Long personId, Person newPerson) throws Exception {
        Optional<Person> data = personRepository.findById(personId);
        if (data.isPresent()) {
            Person person = data.get();
            person.setUsername(newPerson.getUsername());
            person.setFirstName(newPerson.getFirstName());
            person.setLastName(newPerson.getLastName());
            person.setEmail(newPerson.getEmail());
            person.setDob(newPerson.getDob());

            for (Pet pet : person.getPets()) {
                for (Pet newPet : newPerson.getPets()) {
                    if (pet.getId().equals(newPet.getId())) {
                        pet.setLevel(newPet.getLevel());
                        pet.setExperience(newPet.getExperience());
                        pet.setIs_primary(newPet.getIs_primary());
                        petRepository.save(pet);
                    }
                }
            }

            return personRepository.save(person);
        }
        throw new Exception("No person found with id = " + personId);
    }

    void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    public Person addPet(Long personId, Long petId) {
        Person person = findPersonById(personId);
        Optional<Pet> optional = petRepository.findById(petId);
        Pet pet = optional.orElse(null);

        person.addPet(pet);
        return personRepository.save(person);
    }

    public void removePets(Person person) {
        for (Iterator<Pet> pets = person.getPets().iterator(); pets.hasNext(); ) {
            Pet pet = pets.next();
            person.removePet(pet);
        }
    }

    public Iterable<Pet> findPets(Long personId) {
        Person person = findPersonById(personId);
        List<Pet> pets = person.getPets();
        for (Pet pet : pets) {
            Optional<Pokemon> data = pokemonRepository.findById(Long.valueOf(pet.getPokedex_no()));
            Pokemon pokemon = data.orElse(null);
            pet.setName(Objects.requireNonNull(pokemon).getName());
        }
        return pets;
    }

    public Person findPersonByUsername(String username) throws Exception {
        List<Person> persons = (List<Person>) personRepository.findPersonByUsername(username);
        if (!CollectionUtils.isEmpty(persons)) {
            return persons.get(0);
        }
        return null;
    }

    Person findPersonByCredentials(Person person) throws Exception {
        List<Person> persons = (List<Person>) personRepository.findUserByCredentials(person.getUsername(),
                person.getPassword());
        if (!CollectionUtils.isEmpty(persons)) {
            return persons.get(0);
        }
        throw new Exception("User does not exist.");
    }

    public Person login(Person person, HttpSession session) throws Exception {
        List<Person> persons = (List<Person>) personRepository.findUserByCredentials(person.getUsername(),
                person.getPassword());
        if (!CollectionUtils.isEmpty(persons)) {
            Person foundPerson = persons.get(0);
            session.setAttribute("person", foundPerson);
            return foundPerson;
        }
        throw new Exception("No user found with those credentials");
    }
}
