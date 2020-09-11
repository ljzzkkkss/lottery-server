package com.ljzzkkkss.lottery.server.controller;

import com.ljzzkkkss.lottery.server.constants.ReturnType;
import com.ljzzkkkss.lottery.server.model.OptionalParam;
import com.ljzzkkkss.lottery.server.model.ReturnBody;
import com.ljzzkkkss.lottery.server.model.User;
import com.ljzzkkkss.lottery.server.service.OptionalService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class OptionalController {
    @Resource
    private OptionalService optionalService;

    @GetMapping("/private/optional/getList")
    public ReturnBody getList(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        return new ReturnBody(optionalService.getOptionalListByUserId(user.getId()));
    }

    @GetMapping("/private/optional/getOptionalDetailById")
    public ReturnBody getOptionalDetailById(HttpServletRequest request,Integer optionalId){
        User user = (User) request.getSession().getAttribute("user");
        return new ReturnBody(optionalService.getOptionalDetailByOptionalId(optionalId,user.getId()));
    }

    @PostMapping("/private/optional/addOptional")
    public ReturnBody addOptional(HttpServletRequest request, @RequestBody OptionalParam optionalParam){
        User user = (User) request.getSession().getAttribute("user");
        optionalParam.getOptional().setUserId(user.getId());
        optionalService.addOptional(optionalParam)
        return ReturnType.SUCCESS;
    }
}
