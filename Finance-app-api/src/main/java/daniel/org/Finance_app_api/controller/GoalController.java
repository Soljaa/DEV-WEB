package daniel.org.Finance_app_api.controller;

import daniel.org.Finance_app_api.model.*;
import daniel.org.Finance_app_api.service.GoalService;
import daniel.org.Finance_app_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/goals")
public class GoalController {

    @Autowired
    private GoalService goalService;

    @Autowired
    private UserService userService;

    @GetMapping("/user/{userId}")
    public List<GoalDTO> getAllGoalsByUser(@PathVariable Long userId) {
        User user = userService.getUserById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        List<Goal> goals = goalService.getAllGoalsByUser(user);
        List<GoalDTO> goalDTOs = new ArrayList<>();
        for (Goal goal : goals) {
            GoalDTO dto = new GoalDTO(
                    goal.getDescription(),
                    goal.getCurrentAmount(),
                    goal.getTargetAmount(),
                    goal.getGoalDate()
            );
            goalDTOs.add(dto);
        }

        return goalDTOs;
    }

    @PostMapping("/create")
    public Goal createGoal(@RequestBody Goal goal) {
        return goalService.saveGoal(goal);
    }

    @PutMapping("/update/{id}")
    public Goal updateGoal(@PathVariable Long id, @RequestBody Goal updatedGoal) {
        return goalService.updateGoal(id, updatedGoal);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteGoal(@PathVariable Long id) {
        goalService.deleteGoal(id);
    }

}
