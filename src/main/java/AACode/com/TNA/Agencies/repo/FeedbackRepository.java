package AACode.com.TNA.Agencies.repo;

import AACode.com.TNA.Agencies.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    List<Feedback> findByUserIdId(Long userId);
}
