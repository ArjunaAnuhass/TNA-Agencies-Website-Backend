package AACode.com.TNA.Agencies.service;

import AACode.com.TNA.Agencies.model.User;

import java.util.Optional;

public interface UserService {

    public User findUserByJwtToken(String jwt) throws Exception;

    public User findUserByEmail(String email) throws Exception;

    public Optional<User> findUserById(Long id) throws Exception;
}
