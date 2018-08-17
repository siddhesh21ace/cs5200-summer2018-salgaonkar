package edu.northeastern.cs5200.battle;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.northeastern.cs5200.person.GymLeader.GymLeader;
import edu.northeastern.cs5200.person.trainer.Trainer;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Battle")
public class Battle {
    @EmbeddedId
    private BattleId id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @MapsId("trainer_id")
    private Trainer trainer;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @MapsId("gymleader_id")
    private GymLeader gymLeader;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "trainer_won", nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean trainerWon = false;

    private Battle() {
    }

    public Battle(Trainer trainer, GymLeader gymLeader) {
        this.trainer = trainer;
        this.gymLeader = gymLeader;
        this.id = new BattleId(trainer.getId(), gymLeader.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Battle that = (Battle) o;
        return Objects.equals(trainer, that.trainer) &&
                Objects.equals(gymLeader, that.gymLeader);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainer, gymLeader);
    }

    public BattleId getId() {
        return id;
    }

    public void setId(BattleId id) {
        this.id = id;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public GymLeader getGymLeader() {
        return gymLeader;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public void setGymLeader(GymLeader gymLeader) {
        this.gymLeader = gymLeader;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getTrainerWon() {
        return trainerWon;
    }

    public void setTrainerWon(Boolean trainerWon) {
        this.trainerWon = trainerWon;
    }
}
