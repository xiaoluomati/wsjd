package nju.software.check;

import java.util.List;

/**
 * Created by away on 2018/4/4.
 */
public class TypoMsg {

    private String MarkWords;

    private List<Case> Cases;

    public String getMarkWords() {
        return MarkWords;
    }

    public void setMarkWords(String markWords) {
        MarkWords = markWords;
    }

    public List<Case> getCases() {
        return Cases;
    }

    public void setCases(List<Case> cases) {
        Cases = cases;
    }
}
