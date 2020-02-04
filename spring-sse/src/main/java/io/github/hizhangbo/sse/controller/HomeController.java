package io.github.hizhangbo.sse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author Bob
 * @date 2020-02-02 12:04
 */
@RestController
public class HomeController {

    @Autowired
    private HttpServletResponse response;

    @GetMapping(value = "/SSE")
    public void sse(){
        response.setContentType(MediaType.TEXT_EVENT_STREAM_VALUE);
        response.setCharacterEncoding("UTF-8");

        for (int i = 1; i < 10; i++) {
            try {
                response.getWriter().write("event:hello\n");
                response.getWriter().write(String.format("data:%d\n\n",i));
                response.getWriter().flush();
                TimeUnit.SECONDS.sleep(1);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
