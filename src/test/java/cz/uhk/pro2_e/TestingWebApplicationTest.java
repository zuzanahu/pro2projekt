package cz.uhk.pro2_e;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class TestingWebApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    // Tests using MockMvc are for testing whether Spring handles the incoming HTTP request and hands it off to controller
    // This test will fail because we have authorization layer, so that is the correct behavior
    @Test
    void shouldReturnDefaultHomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print()) // Optional: prints request/response details
                .andExpect(status().isOk())
                .andExpect(view().name("index")); // If your controller adds attributes
    }
}
