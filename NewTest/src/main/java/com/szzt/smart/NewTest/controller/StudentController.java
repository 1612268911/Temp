package com.szzt.smart.NewTest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.szzt.smart.NewTest.service.StudentService;
import com.szzt.smart.NewTest.entity.Student;
import org.springframework.web.bind.annotation.*;
import com.szzt.smart.framework.web.model.ResultBody;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <p>
 * 学生表 前端控制器
 * </p>
 *
 * @author 此代码为自动生成
 * @since 2018-07-16
 */
@Api(tags = "学生表接口")
@RestController
@RequestMapping("/NewTest/student")
public class StudentController {

   @Autowired
   private StudentService service;

   @GetMapping("/{id}")
   @ApiOperation(value = "根据ID获取详情", response = ResultBody.class)
   public  ResultBody<Student> get(@PathVariable Long id)
   {
       return ResultBody.success(service.get(id));
   }


    @PostMapping("/add")
    @ApiOperation(value = "新增实体", response = ResultBody.class)
    public ResultBody add(@RequestBody @ApiParam("实体信息") Student  entity)
    {
        service.insert(entity);
        return ResultBody.success();
    }


    @PutMapping("/")
    @ApiOperation(value = "修改实体", response = ResultBody.class)
    public ResultBody update(@RequestBody Student entity)
    {
        service.update(entity);
        return ResultBody.success();
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除实体", response = ResultBody.class)
    public ResultBody deletetUser(@PathVariable @ApiParam("主键ID") Long id)
    {
        service.deleteById(id);
        return ResultBody.success();
    }

}
