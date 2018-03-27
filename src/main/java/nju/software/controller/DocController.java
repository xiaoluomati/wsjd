package nju.software.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by away on 2018/3/26.
 */
@Controller
public class DocController {

    @RequestMapping("/")
    String home(){
        return "redirect:/index";
    }

    @RequestMapping("/index")
    String index(){
        return "index";
    }

    @RequestMapping("/input")
    String docInput(){
        return "input";
    }

    @RequestMapping("/submitdoc")
    String submitdoc(@RequestParam("file") MultipartFile file){
        return "input";
    }
}
