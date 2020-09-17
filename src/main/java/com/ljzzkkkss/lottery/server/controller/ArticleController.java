package com.ljzzkkkss.lottery.server.controller;

import com.ljzzkkkss.lottery.server.model.ReturnBody;
import com.ljzzkkkss.lottery.server.service.ArticleService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Controller
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @ResponseBody
    @GetMapping("/article/getList")
    public ReturnBody getList(String category,Integer page,Integer pageSize){
        return new ReturnBody(articleService.getArticleListByCategoryPage(category,page,pageSize));
    }

    @ResponseBody
    @GetMapping("/article/getDetail")
    public ReturnBody getDetail(Integer id){
        return new ReturnBody(articleService.getArticleById(id));
    }

    @ResponseBody
    @GetMapping("/banner/list")
    public ReturnBody getBannerList(){
        return new ReturnBody(articleService.getBannerList());
    }

    @GetMapping("/news_detail")
    public String news_detail(){
        return "news_detail";
    }
}
