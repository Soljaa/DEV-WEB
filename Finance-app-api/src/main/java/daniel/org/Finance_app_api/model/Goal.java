package daniel.org.Finance_app_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "goals")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O usuário deve ser informado.")
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "description")
    private String description;

    @NotNull(message = "O valor deve ser informado.")
    @DecimalMin(value = "0.01", message = "O valor deve ser maior que 0")
    @Column(name = "target_amount", nullable = false)
    private BigDecimal targetAmount;

    @DecimalMin(value = "0.00", message = "O valor deve ser pelo menos 0")
    @Column(name = "current_amount", nullable = false)
    private BigDecimal currentAmount;

    @NotNull(message = "Uma data de meta deve ser definida.")
    @Column(name = "goal_date", nullable = false)
    private LocalDate goalDate;

    @Column(name = "creation_date", updatable = false, nullable = false)
    private LocalDateTime creationDate = LocalDateTime.now();

    public Goal(User user, String description, BigDecimal currentAmount, BigDecimal targetAmount, LocalDate goalDate) {
        this.user = user;
        this.description = description;
        this.targetAmount = targetAmount;
        this.currentAmount = currentAmount;
        this.goalDate = goalDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(BigDecimal targetAmount) {
        this.targetAmount = targetAmount;
    }

    public BigDecimal getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(BigDecimal currentAmount) {
        this.currentAmount = currentAmount;
    }

    public LocalDate getGoalDate() {
        return goalDate;
    }

    public void setGoalDate(LocalDate goalDate) {
        this.goalDate = goalDate;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
