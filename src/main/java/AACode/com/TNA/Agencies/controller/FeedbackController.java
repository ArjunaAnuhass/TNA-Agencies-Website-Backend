package AACode.com.TNA.Agencies.controller;

import AACode.com.TNA.Agencies.model.Feedback;
import AACode.com.TNA.Agencies.model.User;
import AACode.com.TNA.Agencies.request.PostFeedbackRequest;
import AACode.com.TNA.Agencies.service.FeedbackService;
import AACode.com.TNA.Agencies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;
    private final UserService userService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService, UserService userService) {
        this.feedbackService = feedbackService;
        this.userService = userService;
    }

    @PostMapping(path = "/postFeedback")
    public ResponseEntity<Feedback> postFeedback(@RequestBody PostFeedbackRequest postFeedbackRequest,
                                                 @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Feedback feedback = feedbackService.postFeedback(postFeedbackRequest, user);

        return new ResponseEntity<>(feedback, HttpStatus.CREATED);
    }

    @GetMapping(path = "/getAllFeedbacks")
    public ResponseEntity<List<Feedback>> getAllFeedbacks(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        List<Feedback> feedbacks = feedbackService.getAllFeedback();

        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }
}
