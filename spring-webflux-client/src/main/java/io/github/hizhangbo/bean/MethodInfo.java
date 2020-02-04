package io.github.hizhangbo.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author Bob
 * @date 2020-02-03 22:22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MethodInfo {

    private String url;

    private HttpMethod method;

    private Map<String, Object> params;

    private Mono<?> body;

    private boolean returnFlux;

    private Class<?> returnElementType;
}
