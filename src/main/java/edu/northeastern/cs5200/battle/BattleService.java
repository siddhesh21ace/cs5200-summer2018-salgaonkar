package edu.northeastern.cs5200.battle;

import edu.northeastern.cs5200.person.GymLeader.GymLeader;
import edu.northeastern.cs5200.person.GymLeader.GymLeaderRepository;
import edu.northeastern.cs5200.person.trainer.Trainer;
import edu.northeastern.cs5200.person.trainer.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BattleService {
    private final BattleRepository battleRepository;
    private final TrainerRepository trainerRepository;
    private final GymLeaderRepository gymLeaderRepository;

    @Autowired
    public BattleService(BattleRepository battleRepository,
                         TrainerRepository trainerRepository,
                         GymLeaderRepository gymLeaderRepository) {
        this.battleRepository = battleRepository;
        this.trainerRepository = trainerRepository;
        this.gymLeaderRepository = gymLeaderRepository;
    }

    Battle createBattle(Battle battle) {
        return battleRepository.save(battle);
    }

    List<Battle> findAllBattles() {
        return (List<Battle>) battleRepository.findAll();
    }

    Battle findBattleById(Long battleId) {
        Optional<Battle> data = battleRepository.findById(battleId);
        return data.orElse(null);
    }

    Battle updateBattle(Long battleId, Battle newBattle) throws Exception {
        Optional<Battle> data = battleRepository.findById(battleId);
        if (data.isPresent()) {
            Battle battle = data.get();
            battle.setGymLeader(newBattle.getGymLeader());
            battle.setTrainer(newBattle.getTrainer());
            return battleRepository.save(battle);
        }
        throw new Exception("No battle found with id = " + battleId);
    }

    void deleteBattle(Long id) {
        battleRepository.deleteById(id);
    }

    Trainer addBattleData(Long trainerId, Long gymLeaderId) {
        Optional<Trainer> optionalTrainer = trainerRepository.findById(trainerId);
        Trainer trainer = optionalTrainer.orElse(null);
        Optional<GymLeader> optionalGymLeader = gymLeaderRepository.findById(gymLeaderId);
        GymLeader gymLeader = optionalGymLeader.orElse(null);

        Objects.requireNonNull(trainer).addBattle(gymLeader);
        return trainerRepository.save(trainer);
    }

    Trainer removeBattleData(Long trainerId, Long gymLeaderId) {
        Optional<Trainer> optionalTrainer = trainerRepository.findById(trainerId);
        Trainer trainer = optionalTrainer.orElse(null);
        Optional<GymLeader> optionalGymLeader = gymLeaderRepository.findById(gymLeaderId);
        GymLeader gymLeader = optionalGymLeader.orElse(null);
        Objects.requireNonNull(trainer).removeBattle(gymLeader);
        return trainerRepository.save(trainer);
    }

    Trainer updateBattleData(Long trainerId, Long gymLeaderId) {
        Optional<Trainer> optionalTrainer = trainerRepository.findById(trainerId);
        Trainer trainer = optionalTrainer.orElse(null);
        Optional<GymLeader> optionalGymLeader = gymLeaderRepository.findById(gymLeaderId);
        GymLeader gymLeader = optionalGymLeader.orElse(null);
        Objects.requireNonNull(trainer).updateBattle(gymLeader);
        return trainerRepository.save(trainer);
    }
}
