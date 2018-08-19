package edu.northeastern.cs5200.person.trainer;

import edu.northeastern.cs5200.battle.Battle;
import edu.northeastern.cs5200.order.Order;
import edu.northeastern.cs5200.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

import static edu.northeastern.cs5200.util.Constants.PERSON_ID;

@RestController
public class TrainerContoller {
    private final TrainerService trainerService;
    private final PersonService personService;

    @Autowired
    public TrainerContoller(TrainerService trainerService, PersonService personService) {
        this.trainerService = trainerService;
        this.personService = personService;
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

    @PostMapping("/api/register")
    public Trainer register(@RequestBody Trainer trainer, HttpSession session) throws Exception {
        if (personService.findPersonByUsername(trainer.getUsername()) != null) {
            throw new Exception("Username already exists.");
        } else {
            trainer = trainerService.createTrainer(trainer);
            session.setAttribute(PERSON_ID, trainer.getId());
            return trainer;
        }
    }

}
