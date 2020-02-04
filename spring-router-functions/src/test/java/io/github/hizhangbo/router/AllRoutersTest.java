package io.github.hizhangbo.router;

import io.github.hizhangbo.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AllRoutersTest {

//    @Autowired
//    private WebApplicationContext context;
//
//    private MockMvc mvc;
//
//    @Before
//    public void setUp() throws Exception {
//        //构造MockMvc
//        mvc = MockMvcBuilders.webAppContextSetup(context).build();
//    }


    @Autowired
    private WebTestClient client;


    @BeforeEach
    void setUp() {
    }

    @Test
    public void getAllUser() {
        User user = client.get().uri("/user").exchange().expectStatus().isOk()
                .expectBody(User.class).returnResult().getResponseBody();
        if (user != null) {
            log.info(user.getName());
        } else {
            log.error("NOT FOUND");
        }
    }

    @Test
    public void createUser() {
    }

    @Test
    public void deleteUserById() {
    }
}