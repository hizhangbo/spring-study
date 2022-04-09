package io.github.hizhangbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Bob
 * @date 2020-02-02 12:04
 * Server-Sent Events 简称SSE
 * 服务器向客户端进行单向多次推送，替换WebSocket
 */
@SpringBootApplication
public class SSEApplication {

    public static void main(String[] args) {
        SpringApplication.run(SSEApplication.class, args);
    }
}
