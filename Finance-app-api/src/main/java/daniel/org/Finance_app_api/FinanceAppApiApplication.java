package daniel.org.Finance_app_api;

import daniel.org.Finance_app_api.enumeration.Role;
import daniel.org.Finance_app_api.enumeration.TransactionType;
import daniel.org.Finance_app_api.enumeration.FrequencyType;
import daniel.org.Finance_app_api.model.Transaction;
import daniel.org.Finance_app_api.model.User;
import daniel.org.Finance_app_api.model.Goal;
import daniel.org.Finance_app_api.repository.GoalRepository;
import daniel.org.Finance_app_api.repository.TransactionRepository;
import daniel.org.Finance_app_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class FinanceAppApiApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TransactionRepository transactionRepository;

    @Autowired
	private GoalRepository goalRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(FinanceAppApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        User admin = userRepository.findByUsername("admin");
        if (admin == null) {
            User admUser = new User("admin", passwordEncoder.encode("123"), Role.ADMIN);
            userRepository.save(admUser);
        }

        User user = userRepository.findByUsername("user");
        User basicUser = null;
        if (user == null) {
            basicUser = new User("user", passwordEncoder.encode("123"), Role.USER);
            userRepository.save(basicUser);
        }

        Transaction transaction1 = new Transaction(
                basicUser,
                TransactionType.EXPENSE,
                "Compra de comida",
                new BigDecimal("50.00"),
                LocalDate.now(),
                FrequencyType.OCCASIONAL
        );

        Transaction transaction2 = new Transaction(
				basicUser,
                TransactionType.INCOME,
                "Salário",
                new BigDecimal("1500.00"),
                LocalDate.of(2024, 7, 1),
                FrequencyType.REGULAR
        );

        Transaction transaction3 = new Transaction(
				basicUser,
                TransactionType.EXPENSE,
                "Pagamento de aluguel",
                new BigDecimal("800.00"),
                LocalDate.of(2024, 7, 1),
                FrequencyType.REGULAR
        );

        Transaction transaction4 = new Transaction(
				basicUser,
                TransactionType.INCOME,
                "Bônus trimestral",
                new BigDecimal("500.00"),
                LocalDate.of(2024, 6, 30),
                FrequencyType.OCCASIONAL
        );



        transactionRepository.save(transaction1);
        transactionRepository.save(transaction2);
        transactionRepository.save(transaction3);
        transactionRepository.save(transaction4);

        transactionRepository.save(new Transaction(
                basicUser,
                TransactionType.INCOME,
                "Bônus trimestral de verão",
                new BigDecimal("550.00"),
                LocalDate.of(2024, 7, 15),
                FrequencyType.REGULAR
        ));
        transactionRepository.save(new Transaction(
                basicUser,
                TransactionType.EXPENSE,
                "Aluguel",
                new BigDecimal("1200.00"),
                LocalDate.of(2024, 7, 20),
                FrequencyType.REGULAR
        ));
        transactionRepository.save(new Transaction(
                basicUser,
                TransactionType.INCOME,
                "Prêmio por desempenho",
                new BigDecimal("800.00"),
                LocalDate.of(2024, 7, 25),
                FrequencyType.OCCASIONAL
        ));
        transactionRepository.save(new Transaction(
                basicUser,
                TransactionType.EXPENSE,
                "Supermercado",
                new BigDecimal("250.00"),
                LocalDate.of(2024, 7, 28),
                FrequencyType.OCCASIONAL
        ));
        transactionRepository.save(new Transaction(
                basicUser,
                TransactionType.INCOME,
                "Bônus de fim de ano",
                new BigDecimal("700.00"),
                LocalDate.of(2024, 8, 5),
                FrequencyType.OCCASIONAL
        ));
        transactionRepository.save(new Transaction(
                basicUser,
                TransactionType.EXPENSE,
                "Gasolina",
                new BigDecimal("180.00"),
                LocalDate.of(2024, 8, 10),
                FrequencyType.OCCASIONAL
        ));
        transactionRepository.save(new Transaction(
                basicUser,
                TransactionType.INCOME,
                "Bônus trimestral de outono",
                new BigDecimal("600.00"),
                LocalDate.of(2024, 8, 15),
                FrequencyType.OCCASIONAL
        ));
        transactionRepository.save(new Transaction(
                basicUser,
                TransactionType.EXPENSE,
                "Internet",
                new BigDecimal("90.00"),
                LocalDate.of(2024, 8, 20),
                FrequencyType.REGULAR
        ));
        transactionRepository.save(new Transaction(
                basicUser,
                TransactionType.INCOME,
                "Bônus trimestral de inverno",
                new BigDecimal("550.00"),
                LocalDate.of(2024, 8, 25),
                FrequencyType.OCCASIONAL
        ));
        transactionRepository.save(new Transaction(
                basicUser,
                TransactionType.EXPENSE,
                "Restaurante",
                new BigDecimal("150.00"),
                LocalDate.of(2024, 8, 28),
                FrequencyType.OCCASIONAL
        ));
        transactionRepository.save(new Transaction(
                basicUser,
                TransactionType.INCOME,
                "Bônus por metas alcançadas",
                new BigDecimal("900.00"),
                LocalDate.of(2024, 9, 5),
                FrequencyType.OCCASIONAL
        ));
        transactionRepository.save(new Transaction(
                basicUser,
                TransactionType.EXPENSE,
                "Academia",
                new BigDecimal("200.00"),
                LocalDate.of(2024, 9, 10),
                FrequencyType.REGULAR
        ));
        transactionRepository.save(new Transaction(
                basicUser,
                TransactionType.INCOME,
                "Bônus de primavera",
                new BigDecimal("500.00"),
                LocalDate.of(2024, 9, 15),
                FrequencyType.OCCASIONAL
        ));
        transactionRepository.save(new Transaction(
                basicUser,
                TransactionType.EXPENSE,
                "Telefone",
                new BigDecimal("80.00"),
                LocalDate.of(2024, 9, 20),
                FrequencyType.REGULAR
        ));
        transactionRepository.save(new Transaction(
                basicUser,
                TransactionType.INCOME,
                "Bônus de aniversário",
                new BigDecimal("600.00"),
                LocalDate.of(2024, 9, 25),
                FrequencyType.OCCASIONAL
        ));
        transactionRepository.save(new Transaction(
                basicUser,
                TransactionType.EXPENSE,
                "Roupas",
                new BigDecimal("120.00"),
                LocalDate.of(2024, 9, 28),
                FrequencyType.OCCASIONAL
        ));
        transactionRepository.save(new Transaction(
                basicUser,
                TransactionType.INCOME,
                "Bônus trimestral de verão",
                new BigDecimal("550.00"),
                LocalDate.of(2024, 10, 5),
                FrequencyType.OCCASIONAL
        ));
        transactionRepository.save(new Transaction(
                basicUser,
                TransactionType.EXPENSE,
                "Aluguel",
                new BigDecimal("1200.00"),
                LocalDate.of(2024, 10, 10),
                FrequencyType.REGULAR
        ));
        transactionRepository.save(new Transaction(
                basicUser,
                TransactionType.INCOME,
                "Prêmio por desempenho",
                new BigDecimal("800.00"),
                LocalDate.of(2024, 10, 15),
                FrequencyType.OCCASIONAL
        ));
        transactionRepository.save(new Transaction(
                basicUser,
                TransactionType.EXPENSE,
                "Supermercado",
                new BigDecimal("250.00"),
                LocalDate.of(2024, 10, 20),
                FrequencyType.REGULAR
        ));
        transactionRepository.save(new Transaction(
                basicUser,
                TransactionType.INCOME,
                "Bônus de fim de ano",
                new BigDecimal("700.00"),
                LocalDate.of(2024, 10, 25),
                FrequencyType.OCCASIONAL
        ));
        transactionRepository.save(new Transaction(
                basicUser,
                TransactionType.EXPENSE,
                "Gasolina",
                new BigDecimal("180.00"),
                LocalDate.of(2024, 10, 28),
                FrequencyType.REGULAR
        ));


        Goal goal1 = new Goal(basicUser, "Buy a new laptop", new BigDecimal("0"), new BigDecimal("1500.00"), LocalDate.of(2024, 12, 31));
        Goal goal2 = new Goal(basicUser, "Vacation fund", new BigDecimal("0"), new BigDecimal("3000.00"), LocalDate.of(2025, 6, 15));

        goalRepository.save(goal1);
        goalRepository.save(goal2);
    }

}
