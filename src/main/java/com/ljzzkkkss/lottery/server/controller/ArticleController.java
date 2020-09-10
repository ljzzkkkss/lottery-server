package com.ljzzkkkss.lottery.server.controller;

import com.ljzzkkkss.lottery.server.model.ReturnBody;
import com.ljzzkkkss.lottery.server.service.ArticleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @GetMapping("/article/getList")
    public ReturnBody getList(String category,Integer page,Integer pageSize){
        return new ReturnBody(articleService.getArticleListByCategoryPage(category,page,pageSize));
    }


    @GetMapping("/article/getDetail")
    public ReturnBody getDetail(Integer id){
        return new ReturnBody(articleService.getArticleById(id));
    }
}
