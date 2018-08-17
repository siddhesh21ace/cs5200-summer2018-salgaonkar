package edu.northeastern.cs5200.person.trainer;

import edu.northeastern.cs5200.battle.Battle;
import edu.northeastern.cs5200.order.Order;
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
    public Trainer findTrainerById(@PathVariable("trainerId") Long trainerId) throws Exception {
        return trainerService.findTrainerById(trainerId);
    }

    @PutMapping("/api/trainer/{trainerId}")
    public Trainer updateTrainer(@PathVariable("trainerId") Long trainerId,
                                 @RequestBody Trainer newTrainer) throws Exception {
        return trainerService.updateTrainer(trainerId, newTrainer);
    }

    @DeleteMapping("/api/trainer/{trainerId}")
    public void deleteTrainer(@PathVariable("trainerId") Long id) {
        trainerService.deleteTrainer(id);
    }

    @GetMapping("/api/trainer/{trainerId}/battle")
    public List<Battle> findBattles(@PathVariable("trainerId") Long trainerId) {
        return trainerService.findBattles(trainerId);
    }

    @PutMapping("/api/trainer/{trainerId}/order/{orderId}")
    public Trainer addOrder(@PathVariable("trainerId") Long trainerId,
                            @PathVariable("orderId") Long orderId) {
        return trainerService.addOrder(trainerId, orderId);
    }

    @GetMapping("/api/trainer/{trainerId}/order")
    public Iterable<Order> findOrders(@PathVariable("trainerId") Long trainerId) {
        return trainerService.findOrders(trainerId);
    }

}
