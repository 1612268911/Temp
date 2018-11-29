/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserService
 * Author:   jj
 * Date:     2018/5/12 14:01
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt.service;

import com.cszt.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.cszt.pojo.User;
import com.cszt.repository.UserMapper;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/5/12
 * @since 1.0.0
 */
@Service
public class UserService {
    @Autowired
    private UserMapper um;
    public List<User> select(){
        return um.select();
    }
    public void exportExcel(List<User> userList, OutputStream out, Resource resource){
        Workbook workbook = null;
        InputStream in = null;
        Sheet sheet = null;
        try{
            in = resource.getInputStream();

            workbook = new XSSFWorkbook(in);

            if(workbook!=null){
                sheet = workbook.getSheetAt(0);
            }
            Row row = null;

            Cell cell = null;
            //获取第一行
            row = sheet.getRow(0);
            //总列数
            int cellTotal = row.getLastCellNum();

            int i = 1;
            for(User user:userList){
                row = sheet.createRow(i);
                for(int j=0;j<cellTotal;j++){
                    cell = row.createCell(j);
                    switch (j){
                        case 0:
                            cell.setCellValue(user.getId());
                            break;
                        case 1:
                            cell.setCellValue(user.getName());
                            break;
                        case 2:
                            cell.setCellValue(user.getPwd());
                            break;
                        default:
                            break;
                    }
                }
                i++;
            }
            workbook.write(out);
            System.out.println("导出完成....");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public String importExcel(MultipartFile multipartFile){
        InputStream in = null;
        String fileName = null;
        String fileType = null;
        Workbook workbook = null;
        String errorMessage = null;
        try{
            in = multipartFile.getInputStream();
            //文件名
            fileName = multipartFile.getOriginalFilename();
            //后缀
            fileType = fileName.substring(fileName.indexOf(".")+1,fileName.length());
            //判断文件类型
            if(fileType.equals("xlsx")){
                workbook = new XSSFWorkbook(in);
            } else if(fileType.equals("xls")){
                workbook = new HSSFWorkbook(in);
            }else{
                errorMessage = "文件类型不匹配....";
                return errorMessage;
            }
            Sheet sheet = null;
            if(workbook!=null){
                sheet = workbook.getSheetAt(0);
            }
            Row row = null;
            //行记录
            int rowTotal = 0;
            if(sheet!=null){
                row = sheet.getRow(0);
                rowTotal = sheet.getLastRowNum();
            }
            Cell cell = null;
            //列记录
            int cellTotal = row.getLastCellNum();
            List<User> userList = new ArrayList<User>();
            //行
            for(int i=0;i<rowTotal;i++){
                row = sheet.getRow(1+i);//从第二行开始读数据 i=1
                //列
                User user = new User();
                for(int j=0;j<cellTotal;j++){
                    cell = row.getCell(j);//从第一行开始读数据 j=0
                    String value = ExcelUtil.extacted4cellType(cell);
                    if(StringUtils.isEmpty(value)){
                        errorMessage = "存在第"+(i+2)+"行第"+(j+1)+"列数据为空...";
                        return errorMessage;
                    }
                    //id只能是数字、字段长度限制
                    //。。。。
                    switch (j){
                        case 0:
                           user.setId(Integer.parseInt(value));
                           break;
                        case 1:
                            user.setName(value);
                            break;
                        case 2:
                            user.setPwd(value);
                            break;
                        default:
                            break;
                    }
                }
                userList.add(user);
            }
            //验证重复id
            if(!CollectionUtils.isEmpty(userList)){
                //数据库中的user
                List<User> users = um.select();
                for(User user:userList){
                    for(User user1:users){
                        if(user.getId()==user1.getId()){
                            errorMessage = "存在已经保存的用户id...";
                            return errorMessage;
                        }
                    }
                }
            }
            um.insert(userList);
            errorMessage = "写入成功...";
            return errorMessage;
        }catch (Exception e){
            e.printStackTrace();
            errorMessage = "系统异常...";
        }
        return errorMessage;
    }
    public static void main(String[] args){
        List<String> set1 = new ArrayList<String>();
        List<String> set2 = new ArrayList<String>();

        set1.add("a");
        set1.add("b");
        set1.add("d");

        set2.add("h");
        set2.add("d");
        set2.add("e");

        //交集
//        set1.retainAll(set2);
//        System.out.println("交集是 "+set1);//set1=[]
//        System.out.println("交集是 "+set1==null);//false
        //并集
//        set1.addAll(set2);
//        System.out.println("并集是 "+set1);//[a, b, c, d, e, h]
        //差集
//        set1.removeAll(set2);
//        System.out.println("差集是 "+set1);
        //无重复并集
//        set1.removeAll(set2);
//        System.out.println(set1);
//        System.out.println(set2);
//        set2.addAll(set1);
//        System.out.println("无重复并集是 "+set2);
    }
}
