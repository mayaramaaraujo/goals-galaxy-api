package com.goalsgalaxyapi.usecase;

import com.goalsgalaxyapi.domain.model.Category;
import com.goalsgalaxyapi.domain.model.Goal;
import com.goalsgalaxyapi.domain.model.User;
import com.goalsgalaxyapi.domain.repository.GoalRepository;
import com.goalsgalaxyapi.domain.repository.UserRepository;
import com.goalsgalaxyapi.usecase.model.request.GoalRequestModel;
import com.goalsgalaxyapi.usecase.model.response.GoalResponseModel;
import com.goalsgalaxyapi.usecase.model.response.ResponseModel;
import com.goalsgalaxyapi.utils.DateFormatter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Optional;

public class GoalUseCase {

    GoalRepository goalRepository;
    UserRepository userRepository;

    public GoalUseCase(GoalRepository goalRepository, UserRepository userRepository) {
        this.goalRepository = goalRepository;
        this.userRepository = userRepository;
    }

    public ResponseModel<GoalResponseModel> create(GoalRequestModel request, Long userId) {
       try {
           Optional<User> user = userRepository.findById(userId);

           if(user.isEmpty()) {
               return new ResponseModel<>(false, HttpStatus.NOT_FOUND, "User not exists", null);
           }

           Category category = Category.NOT_DEFINED;

           if (request.category() != null) {
               category = Category.fromString(request.category());
           }

           LocalDateTime deadline = null;

           if (request.deadline() != null) {
              deadline = DateFormatter.stringToLocalDateTime(request.deadline());
           }

           if (deadline != null && deadline.isBefore(LocalDateTime.now())) {
               return new ResponseModel<>(false, HttpStatus.BAD_REQUEST, "Deadline should not be before current date", null);
           }

           Goal newGoal = new Goal(request.name(), request.description(), DateFormatter.format(LocalDateTime.now()), deadline, category, user.get());
           Goal goal = goalRepository.save(newGoal);

           return new ResponseModel<>(true, HttpStatus.CREATED, null,  new GoalResponseModel(goal.getId(), goal.getName(), goal.getDescription(), DateFormatter.localDateTimeToString(goal.getCreatedDate()), DateFormatter.localDateTimeToString(goal.getDeadline()), goal.getCategory(), goal.getTasks(), goal.getUser().getId()));
       } catch (Exception e) {
            return new ResponseModel<>(false, HttpStatus.BAD_REQUEST, e.getMessage(), null);
       }
    }
}
