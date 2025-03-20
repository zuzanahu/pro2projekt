package cz.uhk.pro2_e.service;

import cz.uhk.pro2_e.model.User;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    ArrayList<User> users = new ArrayList<>();

    @Override
    public ArrayList<User> getAllUsers() {
        return users;
    }

    @Override
    public void saveUser(User user) {
        users.add(user);
    }
}
