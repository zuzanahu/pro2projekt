package cz.uhk.pro2_e;

import cz.uhk.pro2_e.controller.BookController;
import cz.uhk.pro2_e.controller.IndexController;
import cz.uhk.pro2_e.controller.UserController;
import cz.uhk.pro2_e.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Pro2EApplicationTests {

    @Autowired
    private IndexController controller;

    @Autowired
    private BookController bookController;

    @Autowired
    private UserController userController;

    //A simple sanity check test that will fail if the application context cannot start.
    @Test
    void contextLoads() {
        //if the context is creating your controller
        assertThat(controller).isNotNull();
        assertThat(bookController).isNotNull();
        assertThat(userController).isNotNull();
    }

}
