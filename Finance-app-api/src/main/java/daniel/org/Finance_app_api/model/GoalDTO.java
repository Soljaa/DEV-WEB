package daniel.org.Finance_app_api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record GoalDTO(String description, BigDecimal currentAmount, BigDecimal targetAmount, LocalDate goalDate) {
}
