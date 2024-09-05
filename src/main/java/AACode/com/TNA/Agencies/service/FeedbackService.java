package AACode.com.TNA.Agencies.service;

import AACode.com.TNA.Agencies.model.Feedback;
import AACode.com.TNA.Agencies.model.User;
import AACode.com.TNA.Agencies.request.PostFeedbackRequest;

import java.util.List;

public interface FeedbackService {

    public Feedback postFeedback(PostFeedbackRequest postFeedbackRequest, User user);

    void deleteFeedback(Long feedbackId) throws Exception;

    public List<Feedback> getAllFeedback();

    public Feedback findFeedbackById(Long id) throws Exception;

    public List<Feedback> getFeedbackByUserId(Long userId) throws Exception;
}
