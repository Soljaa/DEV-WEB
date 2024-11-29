package daniel.org.Finance_app_api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionDTO(Long id, String transactionType, String description, BigDecimal amount, LocalDate transactionDate, String frequencyType) {
}
