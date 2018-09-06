package com.cszt.controller;

import com.cszt.domain.BasicGoods;
import com.cszt.service.BasicGoodService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author lilin
 * @create 2018/9/6 14:47
 * description:
 */
@RestController
public class BasicGoodsController {
    @Autowired
    private BasicGoodService basicGoodService;

    @PostMapping(value = "/addGoods")
    public String addGoods(@RequestBody BasicGoods goods) throws SchedulerException {
        String goodsId = UUID.randomUUID().toString();
        goods.setId(goodsId);
        basicGoodService.addGoods(goods);
        return "插入成功-->goodsId="+goodsId;
    }
}