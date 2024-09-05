package AACode.com.TNA.Agencies.service;

import AACode.com.TNA.Agencies.model.Feedback;
import AACode.com.TNA.Agencies.model.User;
import AACode.com.TNA.Agencies.repo.FeedbackRepository;
import AACode.com.TNA.Agencies.repo.UserRepository;
import AACode.com.TNA.Agencies.request.PostFeedbackRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService{

    private final UserRepository userRepository;
    private final FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackServiceImpl(UserRepository userRepository, FeedbackRepository feedbackRepository) {
        this.userRepository = userRepository;
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public Feedback postFeedback(PostFeedbackRequest postFeedbackRequest, User user) {

        Feedback feedback = new Feedback();
        feedback.setFeedback(postFeedbackRequest.getFeedback());
        feedback.setUserId(user);

        return feedbackRepository.save(feedback);
    }

    @Override
    public void deleteFeedback(Long feedbackId) throws Exception {

        Feedback feedback = findFeedbackById(feedbackId);

        feedbackRepository.delete(feedback);

    }

    @Override
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    @Override
    public Feedback findFeedbackById(Long id) throws Exception {

        Optional<Feedback> optionalFeedback = feedbackRepository.findById(id);

        if (optionalFeedback.isEmpty()){
            throw new Exception("Feedback is not Found!!!");
        }
        return optionalFeedback.get();
    }

    @Override
    public List<Feedback> getFeedbackByUserId(Long userId) throws Exception {

        List<Feedback> feedbacks = feedbackRepository.findByUserIdId(userId);

        if (feedbacks==null){
            throw new Exception("Feedback Not found in this user id" + userId);
        }
        return feedbacks;
    }
}
