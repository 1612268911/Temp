/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: LocalDateTest
 * Author:   jj
 * Date:     2018/7/1 20:09
 * Description: localdate的使用
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

import java.time.*;

/**
 * 〈一句话功能简述〉<br>
 * 〈localdate的使用〉
 *
 * @author jj
 * @create 2018/7/1
 * @since 1.0.0
 */
public class LocalDateTest {
    public static void main(String[] args){
        LocalDate localDate = LocalDate.now();//本地日期
        System.out.println(localDate);
        System.out.println(localDate.getDayOfYear());
        LocalDateTime localDateTime = LocalDateTime.now();//本地时间和日期
        System.out.println(localDateTime);
        System.out.println( localDateTime.getHour());

        LocalDate localDate1 = LocalDate.of(2019, Month.JULY,28);
        System.out.println(localDate1);

        LocalTime localDate2 = LocalTime.of(20,35);
        LocalTime localTime = LocalTime.now();//本地时间 时分秒
        System.out.println(localTime);

        System.out.println("********************************");
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        ZoneId id = ZoneId.of("Europe/Paris");
        ZoneId zoneId = zonedDateTime.getZone();
        System.out.println(zoneId);
    }
}