package daniel.org.Finance_app_api.controller;

import daniel.org.Finance_app_api.model.Transaction;
import daniel.org.Finance_app_api.model.TransactionDTO;
import daniel.org.Finance_app_api.model.User;
import daniel.org.Finance_app_api.service.TransactionService;
import daniel.org.Finance_app_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @GetMapping("/{transactionId}")
    public Optional<Transaction> getTransactionById(@PathVariable Long transactionId) {
        return transactionService.getTransactionById(transactionId);
    }

    @PostMapping("/create/{userId}")
    public Transaction createTransaction(@RequestBody Transaction transaction, @PathVariable Long userId) {
        User user = userService.getUserById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        transaction.setUser(user);
        return transactionService.createTransaction(transaction);
    }

    @PutMapping("/update/{transactionId}")
    public Transaction updateTransaction(@PathVariable Long transactionId, @RequestBody Transaction updatedTransaction) {
        return transactionService.updateTransaction(transactionId, updatedTransaction);
    }

    @DeleteMapping("/delete/{transactionId}")
    public void deleteTransaction(@PathVariable Long transactionId) {
        transactionService.deleteTransaction(transactionId);
    }

    @GetMapping("/users/{userId}")
    public List<TransactionDTO> getAllTransactionsByUser(@PathVariable Long userId) {
        User user = userService.getUserById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        List<Transaction> transactions = transactionService.getAllTransactionsByUser(user);
        List<TransactionDTO> transactionDTOs = new ArrayList<>();
        for (Transaction transaction : transactions) {
            TransactionDTO dto = new TransactionDTO(
                    transaction.getTransactionId(),
                    transaction.getTransactionType().toString(),
                    transaction.getDescription(),
                    transaction.getAmount(),
                    transaction.getTransactionDate(),
                    transaction.getFrequencyType().toString()
            );
            transactionDTOs.add(dto);
        }

        return transactionDTOs;
    }

    @GetMapping("/users/{userId}/date/{date}")
    public List<TransactionDTO> getTransactionsByUserAndDate(@PathVariable Long userId, @PathVariable String date) {
        User user = userService.getUserById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        LocalDate transactionDate = LocalDate.parse(date);
        List<Transaction> transactions = transactionService.getTransactionsByUserAndDate(user, transactionDate);
        List<TransactionDTO> transactionDTOs = new ArrayList<>();
        for (Transaction transaction : transactions) {
            TransactionDTO dto = new TransactionDTO(
                    transaction.getTransactionId(),
                    transaction.getTransactionType().toString(),
                    transaction.getDescription(),
                    transaction.getAmount(),
                    transaction.getTransactionDate(),
                    transaction.getFrequencyType().toString()
            );
            transactionDTOs.add(dto);
        }

        return transactionDTOs;
    }

    @GetMapping("/users/{userId}/pagination")
    public Page<Transaction> getTransactionsByUserPaginated(
            @PathVariable Long userId,
            @RequestParam(required = false) Integer offset,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) String field,
            @RequestParam(required = false) String dir
    ) {
        User user = userService.getUserById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        offset = offset != null ? offset : 0;
        pageSize = pageSize != null ? pageSize : 10;

        if (field != null && dir != null) {
            return transactionService.getTransactionsByUserPaginatedSorted(user, offset, pageSize, field, dir);
        }

        return transactionService.getTransactionsByUserPaginated(user, offset, pageSize);
    }
}
