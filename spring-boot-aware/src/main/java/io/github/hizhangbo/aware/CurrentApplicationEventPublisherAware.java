package io.github.hizhangbo.aware;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import java.util.EventObject;

/**
 * @author Bob
 * @since 2021/3/6 23:59
 */
@Component
public class CurrentApplicationEventPublisherAware implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishEvent(EventObject event) {
        applicationEventPublisher.publishEvent(event);
    }
}
