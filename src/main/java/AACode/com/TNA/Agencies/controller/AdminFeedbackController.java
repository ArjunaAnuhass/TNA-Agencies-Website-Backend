package AACode.com.TNA.Agencies.controller;

import AACode.com.TNA.Agencies.model.Feedback;
import AACode.com.TNA.Agencies.model.User;
import AACode.com.TNA.Agencies.response.MessageResponse;
import AACode.com.TNA.Agencies.service.FeedbackService;
import AACode.com.TNA.Agencies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/admin/feedback")
public class AdminFeedbackController {

    private final UserService userService;
    private final FeedbackService feedbackService;

    @Autowired
    public AdminFeedbackController(UserService userService, FeedbackService feedbackService) {
        this.userService = userService;
        this.feedbackService = feedbackService;
    }


    @DeleteMapping(path = "/{id}/deleteFeedback")
    public ResponseEntity<MessageResponse> deleteFeedback(@RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        feedbackService.deleteFeedback(id);

        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("Feedback Deleted successfully...");

        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/getUserFeedback")
    public ResponseEntity<List<Feedback>> findFeedbackByUserId(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        List<Feedback> feedbacks = feedbackService.getFeedbackByUserId(user.getId());

        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }
}
