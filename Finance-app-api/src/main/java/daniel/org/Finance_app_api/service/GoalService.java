package daniel.org.Finance_app_api.service;

import daniel.org.Finance_app_api.model.Goal;
import daniel.org.Finance_app_api.model.Transaction;
import daniel.org.Finance_app_api.model.User;
import daniel.org.Finance_app_api.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GoalService {

    @Autowired
    private GoalRepository goalRepository;

    public List<Goal> getAllGoalsByUser(User user) {
        return goalRepository.findByUser(user);
    }

    public Optional<Goal> getGoalById(Long id) {
        return goalRepository.findById(id);
    }

    public Goal saveGoal(Goal goal) {
        return goalRepository.save(goal);
    }

    public Goal updateGoal(Long id, Goal updatedGoal) {
        return goalRepository.findById(id).map(goal -> {
            goal.setDescription(updatedGoal.getDescription());
            goal.setTargetAmount(updatedGoal.getTargetAmount());
            goal.setCurrentAmount(updatedGoal.getCurrentAmount());
            goal.setGoalDate(updatedGoal.getGoalDate());
            return goalRepository.save(goal);
        }).orElseThrow(() -> new RuntimeException("Meta não encontrada."));
    }

    public void deleteGoal(Long id) {
        goalRepository.deleteById(id);
    }
}
