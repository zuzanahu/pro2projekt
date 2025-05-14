package cz.uhk.pro2_e.service;

import cz.uhk.pro2_e.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface UserService extends UserDetailsService {

    List<User> getAllUsers();
    void saveUser(User user);
    User getUser(long id);
    void deleteUser(long id);
}
