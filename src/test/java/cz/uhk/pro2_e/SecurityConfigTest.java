package cz.uhk.pro2_e;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import cz.uhk.pro2_e.service.UserService;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityConfigTest {

    // Tests using MockMvc are for testing whether Spring handles the incoming HTTP request and hands it off to controller
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserService userService;

    @Test
    void unauthenticatedUserAccessingProtectedResource_RedirectsToLogin() throws Exception {
        mockMvc.perform(get("/books/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    @WithMockUser(roles = "USER")
    void authenticatedUserWithoutRoleAccessingAdminEndpoint_ReturnsForbidden() throws Exception {
        mockMvc.perform(get("/users/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/403"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void authenticatedAdminAccessingUsersEndpoint_Allowed() throws Exception {
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk());
    }

    @Test
    void loginPage_AccessibleWithoutAuthentication() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void logoutSuccess_RedirectsToLoginWithLogoutParam() throws Exception {
        mockMvc.perform(post("/logout")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?logout"));
    }

    @Test
    @WithMockUser
    void accessDenied_RedirectsTo403Page() throws Exception {
        // Setup user without ADMIN role
        when(userService.loadUserByUsername(anyString())).thenReturn(
                new org.springframework.security.core.userdetails.User(
                        "user", "pass",
                        Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
                )
        );

        mockMvc.perform(get("/users/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/403"));
    }

    @Test
    void formLogin_Successful_RedirectsToHome() throws Exception {
        String encodedPassword = "$2a$10$mF7NFZfW.T4yzsevn864ueFORn81R0oc3OerMVV9fVfoG72SHsZsO";
        when(passwordEncoder.encode("password")).thenReturn(encodedPassword);
        when(userService.loadUserByUsername("user")).thenReturn(
                new org.springframework.security.core.userdetails.User(
                        "user", encodedPassword,
                        Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
                )
        );

        mockMvc.perform(formLogin()
                        .user("user")
                        .password("heslo"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
}

