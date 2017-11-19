package org.ds.msgboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String toIndex(){
        return "redirect:/msgboard";
    }

    @RequestMapping("/msgboard")
    public String msgboradIndex(){
        return "index";
    }

}
