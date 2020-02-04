package io.github.hizhangbo.handler;

import io.github.hizhangbo.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserHandlerTest {
//    @Autowired
//    private WebTestClient client;
//
//
//    @BeforeEach
//    void setUp() {
//    }

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        //构造MockMvc
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void getAllUser() throws Exception {
//        User user = client.get().uri("/user").exchange().expectStatus().isOk()
//                .expectBody(User.class).returnResult().getResponseBody();
//        if (user != null) {
//            log.info(user.getName());
//        } else {
//            log.error("NOT FOUND");
//        }

        mvc.perform(get("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .param("compId", "GDB0100895")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
//                .andExpect(content().string(Matchers.containsString("OK")))
//                .andExpect(jsonPath("$.errorCode", is("1004")));

    }

    @Test
    public void createUser() {
    }

    @Test
    public void deleteUserById() {
    }
}