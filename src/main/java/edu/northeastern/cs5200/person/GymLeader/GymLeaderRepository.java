package edu.northeastern.cs5200.person.GymLeader;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GymLeaderRepository extends CrudRepository<GymLeader, Long> {
}
