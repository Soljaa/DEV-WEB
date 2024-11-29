package daniel.org.Finance_app_api.repository;

import daniel.org.Finance_app_api.model.Goal;
import daniel.org.Finance_app_api.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.persistence.LockModeType;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {

    List<Goal> findByUser(User user);

    @Query("SELECT g FROM Goal g WHERE g.id = :id")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Goal> getGoalById(Long id);
}

