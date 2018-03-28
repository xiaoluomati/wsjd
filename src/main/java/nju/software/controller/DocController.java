package nju.software.controller;

import nju.software.service.DocManagerService;
import nju.software.service.LawManagerService;
import nju.software.vo.LawItemVO;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    String submitdoc(@RequestParam("file") MultipartFile file, Model model) {
        WsModel doc = docManagerService.getContent(file);

        // 空的law, 里面是要取的条目
        List<LawItemVO> lawItemVOList = docManagerService.getLaw(doc.getWscpfxgcModel().getFtModellist());
        // 包含了内容的 law
//        lawItemVOList = lawManagerService.getLaw(lawItemVOList);

        model.addAttribute("docName", getFileNameWithoutSuffix(file));
        model.addAttribute("doc", doc);
        model.addAttribute("lawList", lawItemVOList);
        return "result";
    }

    private String getFileNameWithoutSuffix(MultipartFile file) {
        String name = file.getOriginalFilename();
        int index = name.indexOf(".");
        return name.substring(0, index);
    }

    @Autowired
    DocManagerService docManagerService;

    @Autowired
    LawManagerService lawManagerService;
}
