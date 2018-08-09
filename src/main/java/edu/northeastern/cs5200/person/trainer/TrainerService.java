package edu.northeastern.cs5200.person.trainer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerService {
    private final TrainerRepository trainerRepository;

    @Autowired
    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    Trainer createTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    List<Trainer> findAllTrainers() {
        return (List<Trainer>) trainerRepository.findAll();
    }

    Trainer findTrainerById(int trainerId) throws Exception {
        Optional<Trainer> data = trainerRepository.findById(trainerId);
        if (data.isPresent()) {
            return data.get();
        }
        throw new Exception("No trainer found with id = " + trainerId);
    }

    Trainer updateTrainer(int trainerId, Trainer newTrainer) throws Exception {
        Optional<Trainer> data = trainerRepository.findById(trainerId);
        if (data.isPresent()) {
            Trainer trainer = data.get();
            trainer.setUsername(newTrainer.getUsername());
            trainer.setFirstName(newTrainer.getFirstName());
            trainer.setLastName(newTrainer.getLastName());
            trainer.setEmail(newTrainer.getEmail());
            trainer.setDob(newTrainer.getDob());
            return trainerRepository.save(trainer);
        }
        throw new Exception("No trainer found with id = " + trainerId);
    }

    void deleteTrainer(int id) {
        trainerRepository.deleteById(id);
    }
}
