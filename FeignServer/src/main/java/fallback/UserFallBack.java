/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserFallBack
 * Author:   jj
 * Date:     2018/5/16 9:30
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package fallback;

import feign.hystrix.FallbackFactory;
import com.cszt.mapper.UserFeignClient;
import org.springframework.stereotype.Component;
import com.cszt.pojo.User;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/5/16
 * @since 1.0.0
 */
@Component
public class UserFallBack implements UserFeignClient {
    @Override
    public User getUserclass(int id) {
        User user = new User();
        user.setUsername("访问错误");
        user.setPassword("访问失败...");
        return user;
    }
}
