package com.cszt;

import com.cszt.domain.User;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

/**
 * @author lilin
 * @create 2018/8/29 17:05
 * Description:
 */
@Data
public class UserRegisterEvent extends ApplicationEvent {

    private User user;
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public UserRegisterEvent(Object source,User user) {
        super(source);
        this.user = user;
    }
}