package nju.software.check.typo;

import nju.software.vo.SectionTypoCheckVO;
import org.languagetool.JLanguageTool;
import org.languagetool.language.Chinese;
import org.languagetool.rules.Rule;
import org.languagetool.rules.RuleMatch;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by away on 2018/4/4.
 */
@Component
public class TypoChecker {

    private JLanguageTool langTool;

    private String[] invalidRule = {"wb2", "wa5", "SHI_ADHECTIVE_ERROR", "wb4", "wa3"};
//    private String[] invalidRule = {};

    public TypoChecker() {
        langTool = new JLanguageTool(new Chinese());
    }

    public List<SectionTypoCheckVO> check(String text) {
        if (text == null) {
            return new ArrayList<>();
        }
//        System.out.println("text = " + text);
        List<SectionTypoCheckVO> sectionTypoCheckVOList = new ArrayList<>();
        List<RuleMatch> matches;
        try {
            matches = langTool.check(text);
            for (RuleMatch match : matches) {
                Rule rule = match.getRule();
//                System.out.println(match.getMessage());
                if (!isValidRule(rule)) continue;

                SectionTypoCheckVO sectionTypoCheckVO = new SectionTypoCheckVO();
                int start = match.getFromPos();
                int end = match.getToPos();
                String sentence = match.getSentence().getAnnotations();
                String word = text.substring(start, end);
                String message = getMessage(match.getMessage());

//                System.out.println("message = " + message);
//                System.out.println("id = " + rule.getId());
                sectionTypoCheckVO.setStart(start);
                sectionTypoCheckVO.setEnd(end);
                sectionTypoCheckVO.setSentence(sentence);
                sectionTypoCheckVO.setWord(word);
                sectionTypoCheckVO.setRuleId(rule.getId());
                sectionTypoCheckVO.setMessage(message);
                sectionTypoCheckVOList.add(sectionTypoCheckVO);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sectionTypoCheckVOList;
    }

    private String getMessage(String message) {
        message = message.replace("<suggestion>", " ");
        message = message.replace("</suggestion>", " ");
        return message;
    }

    private boolean isValidRule(Rule rule) {
        String id = rule.getId();
        for (String s : invalidRule) {
            if (s.equals(id)) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        JLanguageTool langTool = new JLanguageTool(new Chinese());
// comment in to use statistical ngram data:
//langTool.activateLanguageModelRules(new File("/data/google-ngram-data"));
        String text = "原审法院经审理查明：2013年7月8日，李炳祥、李炳恕与李炳太共同出具借据两份，分别向丁立德借款120000元、180000元，双方口头约定月利率2%。2014年8月19日之前的利息已经付清。丁立德索款未果，曾于2014年9月24日以李炳太、李炳祥、李炳恕为被告诉至原审法院，要求归还借款及利息。因李炳太涉嫌非法吸收公众存款犯罪，原审法院于2014年12月2日作出（2014）沭开民初字第02091－2号民事裁定书，驳回丁立德的起诉，并将案件移送沭阳县公安局审查处理。后沭阳县公安局以李炳太涉嫌非法吸收公众存款犯罪立案。现丁立德以李炳祥、李炳恕为被告诉至原审法院，要求支持其上述诉请。\n" +
                "诉讼过程中，丁立德自愿变更诉讼请求为请求判决李炳祥、李炳恕归还丁立德借款本金300000元，本案诉讼费由李炳祥、李炳恕承担，对借款利息表示另案主张权利。\n" +
                "原审法院认为：2015年9月1日起施行的《最高人民法院关于审理民间借贷案件适用法律若干问题的规定》第十三条第一款规定：借款人或出借人的借贷行为涉嫌犯罪，或者已经生效的判决认定构成犯罪，当事人提起民事诉讼的，民间借贷合同并不当然无效。人民法院应当根据合同法第五十二条、本规定第十四条之规定，认定民间借贷合同的效力。李炳太涉嫌非法吸收公众存款罪，涉嫌犯罪的当事人单个借款行为不构成犯罪，只有达到一定量后才发生质变，构成犯罪，即犯罪行为与合同行为不重合。李炳太、李炳祥、李炳恕共同向丁立德借款时，各方意思表示真实，丁立德与李炳祥、李炳恕、李炳太之间的借贷关系不违反法律、行政法规的效力性强制性规定，未损害国家、集体、公共利益或者第三人利益，也没有“以合法形式掩盖非法目的”，应认定合法有效。原审法院受理、审理可以“刑民并行”。李炳祥、李炳恕为共同借款人，依法应当承担共同还款责任。李炳祥、李炳恕辩解其为见证人，未提供证据予以证明其主张。故丁立德要求李炳祥、李炳恕归还借款本金的诉讼请求，有事实和法律依据，原审法院予以支持。丁立德在本案中撤回对支付借款利息的诉讼请求，表示另案主张权利，系对其民事权利的处分，符合法律规定，原审法院予以准许。李炳祥、李炳恕的辩解无事实和法律依据，原审法院不予支持。依照《中华人民共和国合同法》第二百零六条、《最高人民法院关于审理民间借贷案件适用法律若干问题的规定》第十三条第一款、《中华人民共和国民事诉讼法》第一百四十二条之规定，判决：李炳祥、李炳恕应于判决发生法律效力之日起十日内向丁立德支付借款本金300000元。\n" +
                "原审判决宣判后，李炳祥、李炳恕不服，向本院提起上诉，请求二审法院依法改判驳回丁立德原审诉讼请求。其上诉的主要理由为：1、原审法院认定李炳祥、李炳恕与李炳太、周敏为共同借款人是错误的。事实上，李炳祥、李炳恕是作为涉案借款的介绍人、证明人在借据上签字的，实际借款人是李炳太。在（2014）沭开民初字第2092号案件庭审笔录中，周敏承认是其与丈夫李炳太借的钱，李炳祥、李炳恕只是介绍人、证明人。另外，从借条签名上也可判断出李炳祥、李炳恕不是借款人，因二人的签名并非签在借款人处，而是签在借款日期的后面，符合介绍人、证明人签字的一般习惯。2、本案适用法律错误。即使李炳祥、李炳恕是共同借款人，本案也应当驳回起诉。本案真正的借款人李炳太已因涉嫌非法吸收公众存款罪被公安机关立案侦查，依据《最高人民法院、最高人民检察院、公安部关于办理非法集资刑事案件适用法律若干问题的意见》第七条及《最高人民法院关于审理民间借贷案件适用法律若干问题的规定》第五条之规定，本案应当驳回起诉。\n" +
                "被上诉人丁立德答辩称，原审判决认定事实清楚，适用法律正确，应判决驳回上诉，维持原判。具体理由如下：1、李炳祥、李炳恕在欠条上签名没有特别注明其他身份，签名的人均应当认定为借款人。因为借款人为多人，书写位置必然不会一致，但不影响作为借款人的认定。2、本案的民事部分事实清晰，并不会因为刑事案件的侦查导致案情无法认定，并不是必须依据刑事案件的结论才能下判决。3、李炳祥、李炳恕本身并不在刑事案件侦查范围，丁立德在一审起诉前也向沭阳县公安局报案，要求将李炳祥、李炳恕列为犯罪嫌疑人进行侦查，公安机关明确予以拒绝。所以本案双方当事人之间的借贷关系明确，可以进行判决。\n" +
                "本案二审的争议焦点为：1、李炳祥、李炳恕是否是涉案借款的共同借款人；2、本案是否为应当驳回起诉的案件。\n" +
                "本院认为，关于第一争议焦点，虽然李炳祥、李炳恕在借条“借款人”处签字，但因李炳太涉嫌非法吸收公众存款罪被立案侦查，故本案的实际借款人是否包含李炳祥、李炳恕仍需经过刑事判决后方可认定。\n" +
                "关于第二争议焦点，《最高人民法院关于审理民间借贷案件适用法律若干问题的规定》第五条之规定，人民法院立案后，发现民间借贷行为本身涉嫌非法集资犯罪的，应当裁定驳回起诉，并将涉嫌非法集资犯罪的线索、材料移送公安机关或者检查机关。因李炳太涉嫌非法吸收公众存款罪已被立案侦查，本案借款行为本身即涉嫌非法吸收公众存款罪，应裁定驳回起诉，原审法院应将本案移送公安机关处理。如果公安机关立案侦查后撤销案件、或者检察机关作出不起诉决定、或者人民法院生效判决认定不构成非法集资犯罪的，丁立德可以就同一事实理由再次向人民法院提起诉讼。";

        List<RuleMatch> matches = langTool.check(text);
        for (RuleMatch match : matches) {
            System.out.println("Potential error at characters " +
                    match.getFromPos() + "-" + match.getToPos() + ": " +
                    match.getMessage());
            System.out.println("text:" + text.substring(match.getFromPos(), match.getToPos()));
            System.out.println("rule " + match.getRule().getCategory());
            System.out.println("rule id " + match.getRule().getId());
//            System.out.println("sentence " + match.getSentence().getAnnotations());
            System.out.println("sentence " + match.getSentence().getText());
            System.out.println("short" + match.getShortMessage());
            System.out.println("Suggested correction(s): " +
                    match.getSuggestedReplacements());
            System.out.println("_______________");
        }
    }
}

//        String url = "http://api.CuoBieZi.net/spellcheck/json_check/json_phrase";
//
//        String sentence = "院经审查认为，本案华泰证券公司提供的《现券买卖成交单》、《持有人，应在案件审理中查明。《最高人民法院关于适条第二款规定，合同对履行地点没有约定或者约定不明确，争议标的为给付货币的，接收货币一方所在地为合同履行地。本案系证券纠纷，争议标的为给付货币，华泰证券公司作为接受货币一方，其住所地为本案的合同履行地。《中华人民共和国民事诉讼法》第二十三条规定，因合同纠纷提起的诉讼，由被告住所地或者合同履行地人民法院管辖。因华泰证券公司住所地在一审法院辖区，故一审法院作为合同履行地法院对本案有管辖权。综上，天威英利公司上诉理由不能成立，其请求本院不予支持";
//
//        JSONObject json = new JSONObject();
//        json.put("content",sentence);
//        json.put("username","tester");
//        json.put("biz_type","show");
//        json.put("mode","advanced");
//        json.put("check_sensitive_word",true);
//
//        Gson gson = new Gson();
//
//        String str = HttpUtil.doPostJson(url, json.toString());
//        TypoMsg typoMsg = gson.fromJson(str, TypoMsg.class);
//        List<Case> cases = typoMsg.getCases();
//        for (Case aCase : cases) {
//            System.out.println(aCase);
//        }
