package mateusz.michal.chat.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mateusz.michal.chat.Model.JsonRespond;
import mateusz.michal.chat.Model.User;
import mateusz.michal.chat.Service.MyUserDetailsService;
import mateusz.michal.chat.Service.RegistrationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = RegistrationController.class)
public class RegistrationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MyUserDetailsService myUserDetailsService;

    @MockBean
    RegistrationService registrationService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("Test for parsing JSON in /apr/register endpoint")
    void JSONShouldBeParsedToJavaObject() throws Exception {
        User user = User.builder().name("Mateusz").email("saka@dsa.com").password("kotlkas@qs3").build();
        mockMvc.perform(post("/api/register")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test response with name_missing error code")
    void testResponseWithNameMissing() throws Exception {
        User user = User.builder().name("").email("saka@dsa.com").password("").build();
        when(registrationService.saveUserToDatabase(user)).thenReturn("name_missing");
        MvcResult mvcResult = mockMvc.perform(post("/api/register")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andReturn();
        JsonRespond error = new JsonRespond("name_missing",false);
        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        assertThat(actualResponseBody).isEqualTo(
                objectMapper.writeValueAsString(error));
    }
}
