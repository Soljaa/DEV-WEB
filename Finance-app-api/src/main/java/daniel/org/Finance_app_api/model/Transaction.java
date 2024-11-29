package daniel.org.Finance_app_api.model;

import daniel.org.Finance_app_api.enumeration.FrequencyType;
import daniel.org.Finance_app_api.enumeration.TransactionType;
import daniel.org.Finance_app_api.repository.TransactionRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O usuário deve ser informado.")
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull(message = "O tipo de transação deve ser informado (expense/income).")
    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    private TransactionType transactionType;

    private String description;

    @NotNull(message = "O valor deve ser informado.")
    @DecimalMin(value = "0.1", message = "O valor deve ser maior que 0")
    @Column(nullable = false)
    private BigDecimal amount;

    @Column(name = "transaction_date")
    private LocalDate transactionDate;

    @NotNull(message = "A frequência de transação deve ser informada (regular/occasional).")
    @Enumerated(EnumType.STRING)
    @Column(name = "frequency_type", nullable = false)
    private FrequencyType frequencyType;

    public Long getTransactionId() {
        return this.id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TransactionType getTransactionType() {return this.transactionType;}

    public void setTransactionType(String transactionType) {
        this.transactionType = TransactionType.valueOf(transactionType);
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getTransactionDate() {
        return this.transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public FrequencyType getFrequencyType() {
        return frequencyType;
    }

    public void setFrequencyType(String frequencyType) {
        this.frequencyType = FrequencyType.valueOf(frequencyType);
    }

    public Transaction(User user,
                       TransactionType transactionType,
                       String description,
                       BigDecimal amount,
                       LocalDate transactionDate,
                       FrequencyType frequencyType) {
        this.user = user;
        this.transactionType = transactionType;
        this.description = description;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.frequencyType = frequencyType;
    }

}
