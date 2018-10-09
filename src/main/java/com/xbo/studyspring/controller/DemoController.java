package com.xbo.studyspring.controller;

import com.xbo.studyspring.model.Demo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DemoController {
    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    public String demo() {
        Demo d = Demo.builder().code("123").name("bob").build();
        log.warn(d.toString());
        return "hello xbo";
    }
}
