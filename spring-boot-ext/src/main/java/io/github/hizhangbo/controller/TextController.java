package io.github.hizhangbo.controller;

import io.github.hizhangbo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Bob
 * @since 2022/4/16 23:54
 */
@Controller
public class TextController {

    @Autowired
    private TestService testService;

    @RequestMapping("/ext0")
    @ResponseBody
    public String ext0() {
        return testService.ext0();
    }
}
