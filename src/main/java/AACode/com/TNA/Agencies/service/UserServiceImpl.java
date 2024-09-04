package AACode.com.TNA.Agencies.service;

import AACode.com.TNA.Agencies.config.JwtProvider;
import AACode.com.TNA.Agencies.model.User;
import AACode.com.TNA.Agencies.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }


    @Override
    public User findUserByJwtToken(String jwt) throws Exception {

        String email = jwtProvider.getEmailFromJwtToken(jwt);
        User user = findUserByEmail(email);

        return user;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {

        User user = userRepository.findByEmail(email);

        if (user==null){
            throw new Exception("User Not Found!!!");
        }
        return user;
    }

    @Override
    public Optional<User> findUserById(Long id) throws Exception {

        Optional<User> user = userRepository.findById(id);

        if (user==null){
            throw new Exception("User Not found by id!!!");
        }
        return user;
    }
}
