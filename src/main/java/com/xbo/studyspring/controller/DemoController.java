package com.xbo.studyspring.controller;

import com.xbo.studyspring.exception.CommonException;
import com.xbo.studyspring.model.Config;
import com.xbo.studyspring.model.Demo;
import com.xbo.studyspring.model.DemoReq;
import com.xbo.studyspring.util.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
public class DemoController {

    @GetMapping("/err")
    public String err() {
        throw new CommonException("01001", "发送异常");
    }

    @GetMapping("/demo/valid")
    public String demoValid(@Valid DemoReq req) {
        //手动校验
        ValidatorUtil.validate(req);
        return req.getCode() + "," + req.getName();
    }
    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    public String demo() {
        Demo d = Demo.builder().code("123").name("bob").build();
        log.warn(d.toString());
        return desc+value;
    }

    @RequestMapping(value = "/config",method = RequestMethod.GET)
    public String config() {
        return config.toString();
    }
    @Autowired
    Config config;

    @Value("${blog.address}")
    String address;

    @Value("${blog.author}")
    String author;

    @Value("${blog.desc}")
    String desc;

    @Value("${blog.value}")
    String value;
}
