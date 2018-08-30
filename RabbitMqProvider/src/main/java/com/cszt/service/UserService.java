package com.cszt.service;

import com.cszt.ExchangeEnum;
import com.cszt.QueueEnum;
import com.cszt.domain.User;
import com.cszt.queue.QueueMessageService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lilin
 * @create 2018/8/30 15:26
 * Description:
 */
@Service
public class UserService {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private QueueMessageService queueMessageService;

    public String send(User user) throws Exception {
        queueMessageService.send(user.getUserId(), ExchangeEnum.USER_REGISTER, QueueEnum.USER_REGISTER);
        return user.getUserId();
    }
}