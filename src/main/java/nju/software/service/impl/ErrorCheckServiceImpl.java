package nju.software.service.impl;

import nju.software.service.ErrorCheckService;
import nju.software.util.JsonParserUtil;
import nju.software.util.XmlParserUtil;
import nju.software.vo.CheckInfoItemVO;
import nju.software.vo.CheckInfoVO;
import nju.software.vo.DocInfoVO;
import nju.software.vo.ErrorType;

import java.util.*;

/**
 * Created by zhuding on 2018/3/28.
 */
public class ErrorCheckServiceImpl implements ErrorCheckService {

    private JsonParserUtil jsonParserUtil;

    private XmlParserUtil xmlParserUtil;

    @Override
    public CheckInfoVO checkError(DocInfoVO docInfoVO) {
        CheckInfoVO checkInfoVO = new CheckInfoVO();
        jsonParserUtil = new JsonParserUtil(docInfoVO.getJsonFileName());
        xmlParserUtil = new XmlParserUtil(docInfoVO.getXmlFileName());
        checkInfoVO.setWS(this.checkWs());
        checkInfoVO.setSSCYR(this.checkSscyr());
        checkInfoVO.setSSJL(this.checkSSJL());
        checkInfoVO.setAJJBQK(this.checkAjjbqk());
        checkInfoVO.setCPFXGC(this.checkCpfxgc());
        return checkInfoVO;
    }


    private List<CheckInfoItemVO> checkWs(){
        List<CheckInfoItemVO> checkInfoItemVOS = new ArrayList<>();
        final String ahRequirements = this.jsonParserUtil.getAhRequirements();
        String ah = this.xmlParserUtil.getAh();
        if(ah == null){
            checkInfoItemVOS.add(new CheckInfoItemVO(ErrorType.JGQS, "缺少案号"));
        }
        //Todo 案号处理比对
        return checkInfoItemVOS;
    }

    private List<CheckInfoItemVO> checkSscyr(){
        List<CheckInfoItemVO> checkInfoItemVOS = new ArrayList<>();
        final List<String> dsrRequirements = this.jsonParserUtil.getDsrRequirements();
        //当事人诉讼地位比对
        List<String> dsr = this.xmlParserUtil.getDsr();
        checkInfoItemVOS.addAll(checkYS("诉讼当事人",dsrRequirements, dsr));

        return checkInfoItemVOS;
    }

    private List<CheckInfoItemVO> checkSSJL(){
        List<CheckInfoItemVO> checkInfoItemVOS = new ArrayList<>();
        final List<String> ssjlRequirements = this.jsonParserUtil.getSsjlRequirements();
        Map<String, String> ssjl = this.xmlParserUtil.getSsjl();
        checkInfoItemVOS.addAll(checkYS("诉讼记录", ssjlRequirements, ssjl.keySet()));
        //Todo 内容校对
        return checkInfoItemVOS;
    }

    private List<CheckInfoItemVO> checkAjjbqk(){
        List<CheckInfoItemVO> checkInfoItemVOS = new ArrayList<>();
        List<String> ajjbqkRequirements = this.jsonParserUtil.getAjjbqkRequirements();
        Map<String, String> ajjbqk = this.xmlParserUtil.getAjjbqk();
        checkInfoItemVOS.addAll(checkYS("案件基本情况", ajjbqkRequirements, ajjbqk.keySet()));
        //Todo 内容校对
        return checkInfoItemVOS;
    }

    private List<CheckInfoItemVO> checkCpfxgc(){
        List<CheckInfoItemVO> checkInfoItemVOS = new ArrayList<>();
        final Map<String, List<String>> cpfxgcRequirements = this.jsonParserUtil.getCpfxgcRequirements();
        //Todo 法律部分缺失检查
        Map<String, String> cpfxgcQt = this.xmlParserUtil.getCpfxgcQt();
        List<String> cpfxgcQtRequirements = new ArrayList<>();
        if(cpfxgcRequirements.containsKey("其他")){
            cpfxgcQtRequirements = cpfxgcRequirements.get("其他");
        }
        checkInfoItemVOS.addAll(checkYS("裁判分析过程", cpfxgcQtRequirements, cpfxgcQt.keySet()));
        //Todo 内容校对
        return checkInfoItemVOS;
    }

    private List<CheckInfoItemVO> checkCpjg(){
        List<CheckInfoItemVO> checkInfoItemVOS = new ArrayList<>();
        final List<String> pjjgRequirements = this.jsonParserUtil.getPjjgRequirements();
        final Map<String, String> pjjg = this.xmlParserUtil.getPjjg();
        checkInfoItemVOS.addAll(checkYS("判决结果", pjjgRequirements, pjjg.keySet()));
        //Todo 内容校对
        return checkInfoItemVOS;
    }


    //Todo 文尾和附录

    private static List<CheckInfoItemVO> checkYS(String name, List<String> requirements, Collection<String> keys){
        List<CheckInfoItemVO> checkInfoItemVOS = new ArrayList<>();
        if(keys.isEmpty() && !requirements.isEmpty()){
            checkInfoItemVOS.add(new CheckInfoItemVO(ErrorType.JGQS, "缺少" + name));
        }else{
            for (String requirement : requirements) {
                if(!keys.contains(requirement)){
                    checkInfoItemVOS.add(new CheckInfoItemVO(ErrorType.YSQS, name + "缺少" + requirement + "项"));
                }
            }
        }
        return checkInfoItemVOS;
    }
}
