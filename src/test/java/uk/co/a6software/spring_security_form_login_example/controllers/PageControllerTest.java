package uk.co.a6software.spring_security_form_login_example.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class PageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldAllowAnyVisitorToAccessPublicPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/public"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.view().name("public"));
    }

    @Test
    public void shouldNotAllowAnyLoggedOutVisitorToAccessPrivatePage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/private"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrlPattern("**/login"));
    }

    @Test
    @WithMockUser(username = "user")
    public void shouldAllowLoggedInUserToAccessPrivatePage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/private"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.view().name("private"));
    }

    @Test
    @WithMockUser(username = "user")
    public void shouldRedirectBackToLoginAfterLogout() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/logout")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login?logout"));

    }
}