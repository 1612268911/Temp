/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserController
 * Author:   jj
 * Date:     2018/5/12 14:02
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt.controller;

import com.cszt.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.cszt.pojo.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/5/12
 * @since 1.0.0
 */
@Controller
public class UserController {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService us;

    @GetMapping("/list")
    @ResponseBody
    public List<User> select(){
        List<User> list = us.select();
        return list;
    }
    @RequestMapping(value = "/downloadDirectoriesSheetExcel", method = RequestMethod.GET)
    public void downloadDirectoriesSheetExcel(HttpServletRequest request,
                                              HttpServletResponse response) {
        org.springframework.core.io.Resource resourcesExcel = new ClassPathResource("导出.xlsx");
        List<User> list = us.select();
        if((list != null) && (!list.isEmpty())){
            OutputStream outputStream = null;
            try {
                request.setCharacterEncoding("UTF-8");
                response.setContentType("application/x-excel");
                response.addHeader("Content-Disposition",
                        "attachment;fileName=" + new String("用户信息.xlsx".getBytes(), "iso-8859-1")); // 设置文件名
                outputStream = response.getOutputStream();
                us.exportExcel(list, outputStream, resourcesExcel);
            } catch (Exception e) {
                log.error("UserController>>>downloadExcel>>>error:" + e.getLocalizedMessage());
            } finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        log.error("UserController>>>downloadExcel>>>outputStream.close error:"
                                + e.getLocalizedMessage());
                    }
                }
            }
        }
    }
    @RequestMapping(value="/importExcel",method = RequestMethod.POST)
    @ResponseBody
    public String importExcel(@RequestParam ("file") MultipartFile multipartFile){
        String message = us.importExcel(multipartFile);
        return message;
    }
}
