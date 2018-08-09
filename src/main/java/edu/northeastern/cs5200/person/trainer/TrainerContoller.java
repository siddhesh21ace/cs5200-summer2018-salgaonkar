package edu.northeastern.cs5200.person.trainer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TrainerContoller {
    private final TrainerService trainerService;

    @Autowired
    public TrainerContoller(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping("/api/trainer")
    public Trainer createTrainer(@RequestBody Trainer trainer) {
        return trainerService.createTrainer(trainer);
    }

    @GetMapping("/api/trainer")
    public List<Trainer> findAllTrainers() {
        return (List<Trainer>) trainerService.findAllTrainers();
    }

    @GetMapping("/api/trainer/{trainerId}")
    public Trainer findTrainerById(@PathVariable("trainerId") int trainerId) throws Exception {
        return trainerService.findTrainerById(trainerId);
    }

    @PutMapping("/api/trainer/{trainerId}")
    public Trainer updateTrainer(@PathVariable("trainerId") int trainerId,
                                 @RequestBody Trainer newTrainer) throws Exception {
        return trainerService.updateTrainer(trainerId, newTrainer);
    }

    @DeleteMapping("/api/trainer/{trainerId}")
    public void deleteTrainer(@PathVariable("trainerId") int id) {
        trainerService.deleteTrainer(id);
    }

}
