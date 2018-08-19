package edu.northeastern.cs5200.pet;

import edu.northeastern.cs5200.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PetController {
    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping("/api/pet")
    public Pet createPet(@RequestBody Pet pet) {
        return petService.createPet(pet);
    }

    @GetMapping("/api/pet")
    public List<Pet> findAllPets() {
        return petService.findAllPets();
    }

    @GetMapping("/api/pet/{petId}")
    public Pet findPetById(@PathVariable("petId") Long petId) throws Exception {
        return petService.findPetById(petId);
    }

    @PutMapping("/api/pet/{petId}")
    public Pet updatePet(@PathVariable("petId") Long petId,
                         @RequestBody Pet newPet) throws Exception {
        return petService.updatePet(petId, newPet);
    }

    @DeleteMapping("/api/pet/{petId}")
    public void deletePet(@PathVariable("petId") Long id) {
        petService.deletePet(id);
    }

    @GetMapping("/api/pet/{petId}/person")
    public Person findPetOwner(@PathVariable("petId") Long petId) {
        return petService.findPetOwner(petId);
    }

    @PutMapping("/api/person/{personId}/pet/{petId}")
    public Pet updatePetWithPerson(@PathVariable("petId") Long petId,
                                   @PathVariable("personId") Long personId,
                                   @RequestBody Pet newPet) throws Exception {
        return petService.updatePetWithPerson(petId, personId, newPet);
    }

    @PostMapping("/api/person/{personId}6" +
            "/pet")
    public Person createPetForPerson(@PathVariable("personId") Long personId, @RequestBody Pet pet) {
        return petService.createPetForPerson(personId, pet);
    }

}
