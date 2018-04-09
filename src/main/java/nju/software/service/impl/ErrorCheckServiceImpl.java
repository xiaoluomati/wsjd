package nju.software.service.impl;

import nju.software.check.AHChecker;
import nju.software.check.typo.TypoChecker;
import nju.software.repository.TemplateRepository;
import nju.software.service.ErrorCheckService;
import nju.software.util.JsonParserUtil;
import nju.software.util.Synonym;
import nju.software.util.XmlParserUtil;
import nju.software.vo.*;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by zhuding on 2018/3/28.
 */
@Service
public class ErrorCheckServiceImpl implements ErrorCheckService {

    private JsonParserUtil jsonParserUtil;

    private XmlParserUtil xmlParserUtil;

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private AHChecker ahChecker;

    @Autowired
    private TypoChecker typoChecker;

    @Override
    public CheckInfoVO checkError(DocInfoVO docInfoVO) {
        CheckInfoVO checkInfoVO = new CheckInfoVO();
        jsonParserUtil = new JsonParserUtil(templateRepository.getJson(docInfoVO.getJsonName()));
//        jsonParserUtil = new JsonParserUtil(docInfoVO.getJsonName());

        xmlParserUtil = new XmlParserUtil(docInfoVO.getXmlFileName());
        checkInfoVO.setWS(this.checkWs());
        checkInfoVO.setSSCYR(this.checkSscyr());
        checkInfoVO.setSSJL(this.checkSSJL());
        checkInfoVO.setAJJBQK(this.checkAjjbqk());
        checkInfoVO.setCPFXGC(this.checkCpfxgc());
        checkInfoVO.setCPJG(this.checkCpjg());
        checkInfoVO.setWW(new ArrayList<CheckInfoItemVO>());
        checkInfoVO.setFL(new ArrayList<CheckInfoItemVO>());
        return checkInfoVO;
    }

    @Override
    public Map<String, List<SectionTypoCheckVO>> checkTypo(WsModel wsModel) {
        Map<String, List<SectionTypoCheckVO>> typoMap = new HashMap<>();

        String wsssjlSegment = wsModel.getWsssjlSegment();
        typoMap.put("ssjl", typoChecker.check(wsssjlSegment));

        String wsajjbqSegment = wsModel.getWsajjbqSegment();
        typoMap.put("ajjbqk", typoChecker.check(wsajjbqSegment));

        String wscpfxgcSegment = wsModel.getWscpfxgcSegment();
        typoMap.put("cpfxgc", typoChecker.check(wscpfxgcSegment));

        String wscpjgSegment = wsModel.getWscpjgSegment();
//        SectionTypoCheckVO sectionTypoCheckVO = new SectionTypoCheckVO();
//        sectionTypoCheckVO.setStart(3);
//        sectionTypoCheckVO.setEnd(9);
//        sectionTypoCheckVO.setWord("test");
        List<SectionTypoCheckVO> check = typoChecker.check(wscpjgSegment);
//        check.add(sectionTypoCheckVO);
        typoMap.put("cpjg", check);

        return typoMap;
    }


    private List<CheckInfoItemVO> checkWs(){
        List<CheckInfoItemVO> checkInfoItemVOS = new ArrayList<>();
        final String ahRequirements = this.jsonParserUtil.getAhRequirements();
        String ah = this.xmlParserUtil.getAh();
        if(ah == null){
            checkInfoItemVOS.add(new CheckInfoItemVO(ErrorType.JGQS, "缺少案号"));
        } else {
            CheckInfoItemVO check = ahChecker.check(ah);
            if (check != null) {
                checkInfoItemVOS.add(check);
            }
        }
        return checkInfoItemVOS;
    }

    private List<CheckInfoItemVO> checkSscyr(){
        List<CheckInfoItemVO> checkInfoItemVOS = new ArrayList<>();
        final List<String> dsrRequirements = this.jsonParserUtil.getDsrRequirements();
        String dsrRequirementsArray = Arrays.toString(dsrRequirements.toArray());
        //当事人诉讼地位比对
        List<String> dsrList = this.xmlParserUtil.getDsr();
//        checkInfoItemVOS.addAll(checkYS("诉讼当事人",dsrRequirements, dsr));
        System.out.println("dsrRequirementsArray = " + dsrRequirementsArray);
        for (String s : dsrList) {
            System.out.println("s = " + s);
            if (!Synonym.isContains(dsrRequirements, s)) {
                String errMsg = "诉讼参与人地位仅限于" + dsrRequirementsArray;
                checkInfoItemVOS.add(new CheckInfoItemVO(ErrorType.YSCW, errMsg));
            }
        }
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
        System.out.println("-----"+name+"-----");
        System.out.println("要求:" + requirements);
        System.out.println("已有:" + keys);
        List<CheckInfoItemVO> checkInfoItemVOS = new ArrayList<>();
        if(keys.isEmpty() && !requirements.isEmpty()){
            checkInfoItemVOS.add(new CheckInfoItemVO(ErrorType.JGQS, "缺少" + name));
        }else{
            for (String requirement : requirements) {
                if(!isMatch(keys, requirement)){
                    checkInfoItemVOS.add(new CheckInfoItemVO(ErrorType.YSQS, "缺少" + requirement + "项"));
                }
            }
        }
        return checkInfoItemVOS;
    }

    private static boolean isMatch(Collection<String> keys, String requirement){
        if(requirement.contains("(")){
            requirement = requirement.substring(0,requirement.indexOf("("));
        }
        for (String key : keys) {
            String s = key;
            if(s.contains("(")){
                s = s.substring(0,s.indexOf("("));
            }
            if(Synonym.isEqual(key, requirement)){
                return true;
            }
        }
        return false;
    }
}
