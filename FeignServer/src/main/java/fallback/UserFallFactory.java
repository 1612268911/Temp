/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserFallFactory
 * Author:   jj
 * Date:     2018/5/17 9:46
 * Description: fallbackfactory
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package fallback;

import feign.hystrix.FallbackFactory;
import com.cszt.mapper.UserFeignClient;
import com.cszt.mapper.UserFeignClientson;
import org.springframework.stereotype.Component;
import com.cszt.pojo.User;

/**
 * 〈一句话功能简述〉<br>
 * 〈fallbackfactory〉
 *
 * @author jj
 * @create 2018/5/17
 * @since 1.0.0
 */
@Component
public class UserFallFactory implements FallbackFactory<UserFeignClient>{
    @Override
    public UserFeignClient create(Throwable throwable) {
        return new UserFeignClient() {
            @Override
            public User getUserclass(int id) {
                User user = new User();
                user.setUsername("throwable:"+throwable.getMessage());
                user.setPassword("访问失败...");
                return user;
            }
        };
    }
}
