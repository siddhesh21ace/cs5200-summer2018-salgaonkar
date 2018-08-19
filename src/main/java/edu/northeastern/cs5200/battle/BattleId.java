package edu.northeastern.cs5200.battle;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class BattleId implements Serializable {

    @Column(name = "trainer_id")
    private Long trainerId;

    @Column(name = "gymleader_id")
    private Long gymLeaderId;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    private BattleId() {
    }

    public BattleId(Long trainerId, Long gymLeaderId) {
        this.trainerId = trainerId;
        this.gymLeaderId = gymLeaderId;
        this.createdDate = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        BattleId that = (BattleId) o;
        return Objects.equals(trainerId, that.trainerId) &&
                Objects.equals(gymLeaderId, that.gymLeaderId) &&
                Objects.equals(createdDate, that.createdDate);

    }

    @Override
    public int hashCode() {
        return Objects.hash(trainerId, gymLeaderId, createdDate);
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
