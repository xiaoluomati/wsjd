package nju.software.controller;

import nju.software.service.DocManagerService;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by away on 2018/3/26.
 */
@Controller
public class DocController {

    @Autowired
    DocManagerService docManagerService;

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
    String submitdoc(@RequestParam("file") MultipartFile file, Model model) {
        WsModel doc = docManagerService.getContent(file);
        model.addAttribute("doc", doc);
        return "result";
    }
}
