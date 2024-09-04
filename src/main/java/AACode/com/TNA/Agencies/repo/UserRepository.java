package AACode.com.TNA.Agencies.repo;

import AACode.com.TNA.Agencies.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String username);
}
