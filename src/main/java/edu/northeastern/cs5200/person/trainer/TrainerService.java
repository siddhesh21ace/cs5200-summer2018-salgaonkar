package edu.northeastern.cs5200.person.trainer;

import edu.northeastern.cs5200.battle.Battle;
import edu.northeastern.cs5200.order.Order;
import edu.northeastern.cs5200.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerService {
    private final TrainerRepository trainerRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public TrainerService(TrainerRepository trainerRepository, OrderRepository orderRepository) {
        this.trainerRepository = trainerRepository;
        this.orderRepository = orderRepository;
    }

    Trainer createTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    List<Trainer> findAllTrainers() {
        return (List<Trainer>) trainerRepository.findAll();
    }

    Trainer findTrainerById(Long trainerId) {
        Optional<Trainer> data = trainerRepository.findById(trainerId);
        return data.orElse(null);
    }

    Trainer updateTrainer(Long trainerId, Trainer newTrainer) throws Exception {
        Optional<Trainer> data = trainerRepository.findById(trainerId);
        if (data.isPresent()) {
            Trainer trainer = data.get();

            trainer.setFirstName(newTrainer.getFirstName());
            trainer.setLastName(newTrainer.getLastName());
            trainer.setUsername(newTrainer.getUsername());
            trainer.setPassword(newTrainer.getPassword());
            trainer.setEmail(newTrainer.getEmail());

            trainer.setBerries(newTrainer.getBerries());
            trainer.setCoins(newTrainer.getCoins());
            trainer.setPoints(newTrainer.getPoints());
            trainer.setPotion(newTrainer.getPotion());

            return trainerRepository.save(trainer);
        }
        throw new Exception("No trainer found with id = " + trainerId);
    }

    void deleteTrainer(Long id) {
        trainerRepository.deleteById(id);
    }

    List<Battle> findBattles(Long trainerId) {
        Trainer trainer = findTrainerById(trainerId);
        return trainer.getBattles();
    }

    public Trainer addOrder(Long trainerId, Long orderId) {
        Trainer trainer = findTrainerById(trainerId);
        Optional<Order> optional = orderRepository.findById(orderId);
        Order order = optional.orElse(null);

        trainer.addOrder(order);
        return trainerRepository.save(trainer);
    }

    public Iterable<Order> findOrders(@PathVariable("trainerId") Long trainerId) {
        Trainer trainer = findTrainerById(trainerId);
        return trainer.getOrders();
    }

}
