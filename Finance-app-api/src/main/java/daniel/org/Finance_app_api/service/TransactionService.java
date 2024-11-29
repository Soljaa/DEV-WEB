package daniel.org.Finance_app_api.service;

import daniel.org.Finance_app_api.model.Transaction;
import daniel.org.Finance_app_api.model.User;
import daniel.org.Finance_app_api.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactionsByUser(User user) {
        return transactionRepository.findByUser(user);
    }

    public List<Transaction> getTransactionsByUserAndDate(User user, LocalDate transactionDate) {
        return transactionRepository.findByUserAndTransactionDate(user, transactionDate);
    }
    public Page<Transaction> getTransactionsByUserPaginated(User user, int offset, int pageSize) {
        return transactionRepository.findByUser(user, PageRequest.of(offset, pageSize));    }

    public Page<Transaction> getTransactionsByUserPaginatedSorted(User user, int offset, int pageSize, String field, String dir) {
        return transactionRepository.findByUser(user, PageRequest.of(offset, pageSize).withSort(Sort.by(dir.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, field)));
    }

    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.getTransactionById(id);
    }

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction updateTransaction(Long id, Transaction updatedTransaction) {
        return transactionRepository.findById(id).map(transaction -> {
            transaction.setDescription(updatedTransaction.getDescription());
            transaction.setAmount(updatedTransaction.getAmount());
            transaction.setTransactionDate(updatedTransaction.getTransactionDate());
            transaction.setTransactionType(updatedTransaction.getTransactionType().toString());
            transaction.setFrequencyType(updatedTransaction.getFrequencyType().toString());
            return transactionRepository.save(transaction);
        }).orElseThrow(() -> new RuntimeException("Transação não encontrada."));
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
}
