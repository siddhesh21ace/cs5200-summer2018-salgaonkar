package edu.northeastern.cs5200.person.GymLeader;

import edu.northeastern.cs5200.battle.Battle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GymLeaderService {
    private final GymLeaderRepository gymLeaderRepository;

    @Autowired
    public GymLeaderService(GymLeaderRepository gymLeaderRepository) {
        this.gymLeaderRepository = gymLeaderRepository;
    }

    GymLeader createGymLeader(GymLeader gymLeader) {
        return gymLeaderRepository.save(gymLeader);
    }

    List<GymLeader> findAllGymLeaders() {
        return (List<GymLeader>) gymLeaderRepository.findAll();
    }

    GymLeader findGymLeaderById(Long gymLeaderId) {
        Optional<GymLeader> data = gymLeaderRepository.findById(gymLeaderId);
        return data.orElse(null);
    }

    GymLeader updateGymLeader(Long gymLeaderId, GymLeader newGymLeader) throws Exception {
        Optional<GymLeader> data = gymLeaderRepository.findById(gymLeaderId);
        if (data.isPresent()) {
            GymLeader gymLeader = data.get();
            gymLeader.setUsername(newGymLeader.getUsername());
            gymLeader.setFirstName(newGymLeader.getFirstName());
            gymLeader.setLastName(newGymLeader.getLastName());
            gymLeader.setEmail(newGymLeader.getEmail());
            gymLeader.setDob(newGymLeader.getDob());
            gymLeader.setPassword(newGymLeader.getPassword());
            gymLeader.setBadge(newGymLeader.getBadge());
            gymLeader.setRegion(newGymLeader.getRegion());
            gymLeader.setType(newGymLeader.getType());

            return gymLeaderRepository.save(gymLeader);
        }
        throw new Exception("No gymLeader found with id = " + gymLeaderId);
    }

    void deleteGymLeader(Long id) {
        gymLeaderRepository.deleteById(id);
    }

    List<Battle> findBattles(Long gymLeaderId) {
        GymLeader gymLeader = findGymLeaderById(gymLeaderId);
        return gymLeader.getBattles();
    }
}
