package com.example.logindemo.controller;

import com.example.logindemo.pojo.TmtPhaseType;
import com.example.logindemo.service.TmtPhaseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 阶段分类表 前端控制器
 * </p>
 *
 * @author 此代码为自动生成
 * @since 2018-05-24
 */
@Controller
@RequestMapping("/demo/tmtPhaseType")
public class TmtPhaseTypeController {

   @Autowired
   private TmtPhaseTypeService service;

   @GetMapping("/jj")
   @ResponseBody
   public TmtPhaseType get(String id)
   {
      TmtPhaseType tmtPhaseType = service.getTmtPhaseType(id);
      System.out.println("tmtPhaseType="+tmtPhaseType);
       return tmtPhaseType;
   }
}
