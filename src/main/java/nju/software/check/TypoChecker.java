package nju.software.check;

import com.google.gson.Gson;
import net.sf.json.JSONObject;
import nju.software.util.HttpUtil;

import java.util.List;

/**
 * Created by away on 2018/4/4.
 */
public class TypoChecker {

    public static void main(String[] args) {
        String url = "http://api.CuoBieZi.net/spellcheck/json_check/json_phrase";

        String sentence = "院经审查认为，本案华泰证券公司提供的《现券买卖成交单》、《持有人，应在案件审理中查明。《最高人民法院关于适条第二款规定，合同对履行地点没有约定或者约定不明确，争议标的为给付货币的，接收货币一方所在地为合同履行地。本案系证券纠纷，争议标的为给付货币，华泰证券公司作为接受货币一方，其住所地为本案的合同履行地。《中华人民共和国民事诉讼法》第二十三条规定，因合同纠纷提起的诉讼，由被告住所地或者合同履行地人民法院管辖。因华泰证券公司住所地在一审法院辖区，故一审法院作为合同履行地法院对本案有管辖权。综上，天威英利公司上诉理由不能成立，其请求本院不予支持";

        JSONObject json = new JSONObject();
        json.put("content",sentence);
        json.put("username","tester");
        json.put("biz_type","show");
        json.put("mode","advanced");
        json.put("check_sensitive_word",true);

        Gson gson = new Gson();

        String str = HttpUtil.doPostJson(url, json.toString());
        TypoMsg typoMsg = gson.fromJson(str, TypoMsg.class);
        List<Case> cases = typoMsg.getCases();
        for (Case aCase : cases) {
            System.out.println(aCase);
        }
    }
}
