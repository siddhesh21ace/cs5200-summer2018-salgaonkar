package edu.northeastern.cs5200.pet;

import edu.northeastern.cs5200.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    private final PetRepository petRepository;

    @Autowired
    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    Pet createPet(Pet pet) {
        return petRepository.save(pet);
    }

    List<Pet> findAllPets() {
        return (List<Pet>) petRepository.findAll();
    }

    Pet findPetById(Long petId) {
        Optional<Pet> data = petRepository.findById(petId);
        return data.orElse(null);
    }

    Pet updatePet(Long petId, Pet newPet) throws Exception {
        Optional<Pet> data = petRepository.findById(petId);
        if (data.isPresent()) {
            Pet pet = data.get();
            pet.setExperience(newPet.getExperience());
            pet.setLevel(newPet.getLevel());
            pet.setPerson(newPet.getPerson());
            pet.setPokedex_no(newPet.getPokedex_no());
            return petRepository.save(pet);
        }
        throw new Exception("No pet found with id = " + petId);
    }

    void deletePet(Long id) {
        petRepository.deleteById(id);
    }

    public Person findPetOwner(Long petId) {
        Pet pet = findPetById(petId);
        return pet.getPerson();
    }

}
