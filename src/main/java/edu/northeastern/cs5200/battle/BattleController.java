package edu.northeastern.cs5200.battle;

import edu.northeastern.cs5200.person.trainer.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BattleController {
    private final BattleService battleService;

    @Autowired
    public BattleController(BattleService battleService) {
        this.battleService = battleService;
    }

    @PostMapping("/api/battle")
    public Battle createBattle(@RequestBody Battle battle) {
        return battleService.createBattle(battle);
    }

    @GetMapping("/api/battle")
    public List<Battle> findAllBattles() {
        return battleService.findAllBattles();
    }

    @GetMapping("/api/battle/{battleId}")
    public Battle findBattleById(@PathVariable("battleId") Long battleId) throws Exception {
        return battleService.findBattleById(battleId);
    }

    @PutMapping("/api/battle/{battleId}")
    public Battle updateBattle(@PathVariable("battleId") Long battleId,
                               @RequestBody Battle newBattle) throws Exception {
        return battleService.updateBattle(battleId, newBattle);
    }

    @DeleteMapping("/api/battle/{battleId}")
    public void deleteBattle(@PathVariable("battleId") Long id) {
        battleService.deleteBattle(id);
    }

    @PostMapping("/api/trainer/{trainerId}/gymLeader/{gymLeaderId}")
    Trainer addBattleData(@PathVariable("trainerId") Long trainerId,
                          @PathVariable("gymLeaderId") Long gymLeaderId) {
        return battleService.addBattleData(trainerId, gymLeaderId);
    }

    @PutMapping("/api/trainer/{trainerId}/gymLeader/{gymLeaderId}")
    Trainer updateBattleData(@PathVariable("trainerId") Long trainerId,
                             @PathVariable("gymLeaderId") Long gymLeaderId) {
        return battleService.updateBattleData(trainerId, gymLeaderId);
    }

    @DeleteMapping("/api/trainer/{trainerId}/gymLeader/{gymLeaderId}")
    Trainer removeBattleData(@PathVariable("trainerId") Long trainerId,
                             @PathVariable("gymLeaderId") Long gymLeaderId) {
        return battleService.removeBattleData(trainerId, gymLeaderId);
    }
}
