package daniel.org.Finance_app_api.repository;

import daniel.org.Finance_app_api.model.Transaction;
import daniel.org.Finance_app_api.model.User;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long>, PagingAndSortingRepository<Transaction, Long> {
    List<Transaction> findByUser(User user);

    @Query("SELECT t FROM Transaction t WHERE t.user = :user AND t.transactionDate = :transactionDate")
    List<Transaction> findByUserAndTransactionDate(User user, LocalDate transactionDate);

    @Query("SELECT t FROM Transaction t WHERE t.user = :user")
    Page<Transaction> findByUser(User user, Pageable pageable);

    @Query("SELECT t FROM Transaction t WHERE t.id = :id")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Transaction> getTransactionById(Long id);

}
