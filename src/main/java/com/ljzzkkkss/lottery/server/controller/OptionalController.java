package com.ljzzkkkss.lottery.server.controller;

import com.ljzzkkkss.lottery.server.constants.ReturnType;
import com.ljzzkkkss.lottery.server.model.Note;
import com.ljzzkkkss.lottery.server.model.OptionalParam;
import com.ljzzkkkss.lottery.server.model.ReturnBody;
import com.ljzzkkkss.lottery.server.model.User;
import com.ljzzkkkss.lottery.server.service.OptionalService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

@Controller
public class OptionalController {
    @Resource
    private OptionalService optionalService;

    @GetMapping("//private/betting_order")
    public String betting_order(){
        return "betting_order";
    }

    @GetMapping("/scheme")
    public String scheme(){
        return "scheme";
    }

    @GetMapping("/private/leave_message")
    public String leave_message(){
        return "leave_message";
    }

    @ResponseBody
    @PostMapping("/private/optional/addOptional")
    public ReturnBody addOptional(HttpServletRequest request, @RequestBody OptionalParam optionalParam){
        User user = (User) request.getSession().getAttribute("user");
        optionalParam.getOptional().setUserId(user.getId());
        optionalService.addOptional(optionalParam);
        return ReturnType.SUCCESS;
    }

    @ResponseBody
    @PostMapping("/private/optional/sendOptional")
    public ReturnBody sendOptional(HttpServletRequest request, @RequestBody OptionalParam optionalParam){
        User user = (User) request.getSession().getAttribute("user");
        optionalParam.getOptional().setUserId(user.getId());
        optionalService.sendOptional(optionalParam);
        return ReturnType.SUCCESS;
    }

    @ResponseBody
    @PostMapping("/private/optional/sendMessage")
    public ReturnBody sendMessage(HttpServletRequest request, @RequestBody Note note){
        User user = (User) request.getSession().getAttribute("user");
        note.setUserId(user.getId());
        optionalService.sendMessage(note);
        return ReturnType.SUCCESS;
    }

    @ResponseBody
    @GetMapping("/optional/matchList")
    public ReturnBody getOddByMatchId() throws ParseException {
        return new ReturnBody(optionalService.getMatchNotStartList());
    }

    @ResponseBody
    @GetMapping("/private/optional/getNoteList")
    public ReturnBody getNoteList(HttpServletRequest request,Integer pageIndex,Integer pageSize) {
        User user = (User) request.getSession().getAttribute("user");
        return new ReturnBody(optionalService.getNoteList(user.getId(),pageIndex,pageSize));
    }
}
