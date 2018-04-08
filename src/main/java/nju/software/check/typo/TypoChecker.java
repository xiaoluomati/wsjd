package nju.software.check.typo;

import nju.software.vo.TypoCheckVO;
import org.languagetool.JLanguageTool;
import org.languagetool.language.Chinese;
import org.languagetool.rules.RuleMatch;

import java.io.IOException;
import java.util.List;

/**
 * Created by away on 2018/4/4.
 */
public class TypoChecker {


    public List<String> check(String content) {
        JLanguageTool langTool = new JLanguageTool(new Chinese());
        String text = "";

        List<RuleMatch> matches;
        try {
            matches = langTool.check(text);
            for (RuleMatch match : matches) {
                TypoCheckVO typoCheckVO = new TypoCheckVO();
                int start = match.getFromPos();
                int end = match.getToPos();
                String sentence = match.getSentence().getAnnotations();
                typoCheckVO.setStart(start);
                typoCheckVO.setEnd(end);
                typoCheckVO.setSentence(sentence);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) throws IOException {
        JLanguageTool langTool = new JLanguageTool(new Chinese());
// comment in to use statistical ngram data:
//langTool.activateLanguageModelRules(new File("/data/google-ngram-data"));
        String text = "说起错别字，通常想到的可能是“干静（干净）”、“烟洒（烟酒）”、“穿带（穿戴）”、“竟赛（竞赛）”、“既使（即使）”、“园满（圆满）”、“分亨（分享）”、“防碍（妨碍）”、“出奇不意（出其不意）”之类。谁要是写了这样的错别字，在学校里会被老师纠正，在公众场合会被人笑话，自己也会觉得不好意思。可是，另有一类错别字，学校不考，老师不管，出现在大街上人们视而不见，自己写错被别人指出时也往往无动于衷。这一类错字还真不少，你不用找它，它会来找你的。\n" +
                "\n" +
                "信手拈来的真实例子\n" +
                "（以下在需要说明之处，蓝字为繁体，红字为错别字）\n" +
                "\n" +
                "坐在一家中华老字号里吃 X X（该店特色食品），抬头看见墙上的牌匾，红底金字制作精良，用流畅的行书繁体字写着：“X X，古人雲，天下通食也……”，竟然不知道繁体中的“雲”字是指天上的云彩，而表示说的意思则要用“云”，这家中华老字号真可谓不知所云哪。\n" +
                "\n" +
                "走在大街上路过一家中药店，门面装潢得古色古香，雕梁画栋，华佗和孙思邈浮雕像下的生平是用简体书写，而横匾与楹联却是繁体，上联是：“華佗故裏承同仁遵古訓”。故里是故乡的意思，在繁体中不能用里外的“裏”，应当写做“故里”才是。这样浅显的古文用字尚且弄不清楚，怕是难以读懂古代医书，那又怎样去“遵古訓”呢？\n" +
                "\n" +
                "女儿去一个滨海城市参加夏令营，带回来一本该市观光手册，装帧精美，繁体印刷。拿起来随意浏览，每翻几页就令人叹一口气，不是赞叹风景的优美，而是惊讶书里的错别字之多。比如“旅游”、“制造”、“建筑”，这些在简体中正确的写法，在繁体文章中却是错误（应为“旅遊”、“製造”、“建築”）。真不知港台游客是否能读得下去？\n" +
                "\n" +
                "乘双层公交车出游，一幢高楼顶上的巨幅招牌跳入眼帘，想躲都躲不开。那是三个正楷大字，第一个字的繁简体写法相同，不去说它；第二个字的繁简体写法不同，这里用的是繁体；第三个字的繁简体写法也不同，这里写的却是简体。这样一幅招牌，既不合简体的规矩，也不合繁体的规矩，然而它巍然屹立在那里，俨然有着合法的身份。\n" +
                "\n" +
                "有人可能会说，这些字固然写得不对，你却也不必大惊小怪，这些不完美也无伤大雅嘛。我也曾是这么想的，直到我有了下面这一回遭遇。\n" +
                "\n" +
                "是可忍，孰不可忍\n" +
                "住处附近有个客流量很大的去处，各色人等人来人往，像机场一般热闹。营业大厅宽敞气派，装修华丽，墙上有巨幅壁画。其中一幅描绘了二十多位中国历史名人，为了帮助观众识别，上面刻有画中人名单。第一次细看此画时我想，祖国历史上最有名的二三十人，他们的名字我自然都该认得出，尽管那名单是用繁体写成。可当我的目光扫过名单时，头上不由得冒汗了：有个名字闻所未闻！愣了好一会儿，我才省悟过来这个“嶽飛”指的是谁，──那位家喻戶曉的民族英雄，名字居然被错写成这副模样！还有一个“範仲淹”也写得不对。于是我找到那地方的在线留言簿，发去一条短信：“贵处壁画中‘嶽飛’和‘範仲淹’这两个人名有误，影响不好，望予更正。”\n" +
                "\n" +
                "我满以为，看到我的提醒之后，他们会对自己的疏忽表示歉意并设法改正的。但我完全错了，他们回复说：“感谢您的关注。本中心大厅壁画上岳飞和范仲淹的姓氏使用的是繁体字。”我哭笑不得，只好又写了一封回复向他们讲解，在繁体中姓岳的岳字不能写作“山嶽”的“嶽”，姓范的范字也不能写做“範圍”的“範”，此二人名的正确繁体写法是“岳飛”和“范仲淹”。这次可就石沉大海、再无回音了，几年时间过去，壁画上那张名单依然如故。我的一位外国朋友，他学过中文且繁简皆通，也看到了这幅壁画，他对我说，“岳飞还在监狱里呢？！”（“嶽”的山字头下面是个繁体的狱字。）我哑口无言，欲哭无泪：这恐怕是创了历史记录，九百年来大概没有人敢把岳飞的姓氏写错成这个样子，而且还拒不更正！以前在学“数典忘祖”这个成语时总觉得那情景难以想象，而眼前竟是这样一个活生生的实例。在大雅之堂上数典忘祖，这种耻辱我看有甚于割地求和！\n" +
                "\n" +
                "反复思索这件事，渐渐地我觉出，比写错几个字更可怕的是那种对待繁体字的态度。本来，学简体字长大的一代，写错一些繁体字在所难免，但是当别人指出可能有错时，却不肯花一点时间去弄弄清楚是否如此，而是简单地宣布那是繁体字就万事大吉、再也不闻不问了。那态度就好像繁体字是拉丁文，自己完全没有能力辨别；又好像繁体字不是自己祖先的文字，写对写错与自己毫不相干。这样一种冷漠，让我感到阵阵心寒。\n" +
                "\n" +
                "不过，我撞上的，恐怕还只是冰山的一角。\n" +
                "\n" +
                "为什么这类错别字越来越多\n" +
                "在简体字推行初期，六十年代、七十年代、甚至八十年代，这样的问题还很少见，而如今这一类错别字却像雨后野草，满目皆是，而且愈演愈烈。为什么呢？对比今昔可以看到，最近二十来年，我们身边的文字使用环境已经发生了重大的变化：\n" +
                "\n" +
                "其一，使用繁体字的需求不断增长。\n" +
                "\n" +
                "过去，繁体字主要用于古籍整理和海外交往。如今，更多的产品销往海外，更多的游客来华访问，哪个商家不想打开海外市场？即使只面向国内，随着对传统文化兴趣的增长，人们越来越多地用繁体字书写店名和广告、印制简介和名片、……，以求得古色古香、底蕴深厚的印象。互联网的普及，大大增加了人们接触繁体字的机会，而繁体字的一些独特优点（比如在平面设计中更为美观）日益为在简体字中长大的一代所认识，使得不少年轻人对繁体字跃跃欲试。繁体字已不再是“港台专用文字”，而是全中国日常文字环境中活生生的一部分，想想看，哪一天你没有遇见一些繁体字呢？可是与此同时，会正确使用繁体字的人却在减少。过去，在需要用繁体字的场合，还有自幼使用繁体字的一代人把关，如今他们大多已经不在了。比他们年轻些的、在繁简过渡时期学会阅读的一代，至少知道岳飞的名字用繁体来写该是什么样子，可他们也正在老去。正在逐渐成为当今社会中坚的年青一代，是在完全的简体字环境(以及不大正确的繁体字环境)中长大的，就整体来说，他们对繁体字是生疏的，似懂非懂。\n" +
                "\n" +
                "其二，信息科技的发展使汉字已不必手写。\n" +
                "\n" +
                "过去的文稿需要手写，而能手写繁体字的人通常都有着相当的阅读和书写繁体字的经验，不会分不清繁体中的“裏”和“里”、“雲”和“云”。如今，不必知道笔画详情也可以打出汉字来，甚至完全不识繁体字的人，也可以先用简体字打出一篇文章，再点击一下就将全文翻成繁体。信息科技所带来的方便，大大降低了用繁体字写作的门槛，使不少人误以为使用电脑就可以轻易而准确地将简体字转换为繁体字。于是，没有学习过繁体中文基本常识的人们越来越多地依赖信息技术来读写繁体字。这就使得今日繁体文章的作者之中有相当多的人，没有能力辨认自己文章中违反繁体字常识的初级错误。\n" +
                "\n" +
                "然而，需要指出的是，需求的增长和技术的进步未必就一定会使繁体错别字大量增加；造成目前问题的根本原因，还是人们心中的一些观念不符合当前的现实。本文的下半部分将分析探讨造成问题现状的两种主要错误观念，有兴趣的读者且听下回分解。";

        List<RuleMatch> matches = langTool.check(text);
        for (RuleMatch match : matches) {
            System.out.println("Potential error at characters " +
                    match.getFromPos() + "-" + match.getToPos() + ": " +
                    match.getMessage());
            System.out.println("text:" + text.substring(match.getFromPos(), match.getToPos()));
            System.out.println("rule " + match.getRule().getCategory());
            System.out.println("rule id " + match.getRule().getId());
            System.out.println("sentence " + match.getSentence().getAnnotations());
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
