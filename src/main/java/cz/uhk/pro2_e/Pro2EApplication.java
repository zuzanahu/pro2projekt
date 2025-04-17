package cz.uhk.pro2_e;

import cz.uhk.pro2_e.model.User;
import cz.uhk.pro2_e.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Pro2EApplication {

    private final UserService userService;

    @Autowired
    public Pro2EApplication(UserService userService) {
        this.userService = userService;
    }

    @Bean
    CommandLineRunner init() {
        return args -> {
            addUser("User", "user", "heslo", "USER");
            addUser("Admin", "admin", "heslo", "ADMIN");
        };
    }

    private void addUser(String name, String username, String password, String role) {
        User user = new User();
        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        userService.saveUser(user);
    }

    public static void main(String[] args) {
        SpringApplication.run(Pro2EApplication.class, args);
    }

}
