package edu.northeastern.cs5200.person.GymLeader;

import edu.northeastern.cs5200.battle.Battle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GymLeaderController {

    private final GymLeaderService gymLeaderService;

    @Autowired
    public GymLeaderController(GymLeaderService gymLeaderService) {
        this.gymLeaderService = gymLeaderService;
    }

    @PostMapping("/api/gymLeader")
    public GymLeader createGymLeader(@RequestBody GymLeader gymLeader) {
        return gymLeaderService.createGymLeader(gymLeader);
    }

    @GetMapping("/api/gymLeader")
    public List<GymLeader> findAllGymLeaders() {
        return (List<GymLeader>) gymLeaderService.findAllGymLeaders();
    }

    @GetMapping("/api/gymLeader/{gymLeaderId}")
    public GymLeader findGymLeaderById(@PathVariable("gymLeaderId") Long gymLeaderId) throws Exception {
        return gymLeaderService.findGymLeaderById(gymLeaderId);
    }

    @PutMapping("/api/gymLeader/{gymLeaderId}")
    public GymLeader updateGymLeader(@PathVariable("gymLeaderId") Long gymLeaderId,
                                     @RequestBody GymLeader newGymLeader) throws Exception {
        return gymLeaderService.updateGymLeader(gymLeaderId, newGymLeader);
    }

    @DeleteMapping("/api/gymLeader/{gymLeaderId}")
    public void deleteGymLeader(@PathVariable("gymLeaderId") Long id) {
        gymLeaderService.deleteGymLeader(id);
    }

    @GetMapping("/api/gymLeader/{gymLeaderId}/battle")
    public List<Battle> findBattles(@PathVariable("gymLeaderId") Long gymLeaderId) {
        return gymLeaderService.findBattles(gymLeaderId);
    }

}
