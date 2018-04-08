package nju.software.check.typo;

/**
 * Created by away on 2018/4/4.
 */
public class Case {

    private String Error;
    private String Tips;
    private String Sentence;
    private String ErrorInfo;
    private String MarkType;
    private String ErrLevel;
    private String WordsLen;
    private String InnerId;
    private String Pos;
    private boolean ReviewWords;

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }

    public String getTips() {
        return Tips;
    }

    public void setTips(String tips) {
        Tips = tips;
    }

    public String getSentence() {
        return Sentence;
    }

    public void setSentence(String sentence) {
        Sentence = sentence;
    }

    public String getErrorInfo() {
        return ErrorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        ErrorInfo = errorInfo;
    }

    public String getPos() {
        return Pos;
    }

    public void setPos(String pos) {
        Pos = pos;
    }

    public String getMarkType() {
        return MarkType;
    }

    public void setMarkType(String markType) {
        MarkType = markType;
    }

    public String getErrLevel() {
        return ErrLevel;
    }

    public void setErrLevel(String errLevel) {
        ErrLevel = errLevel;
    }

    public String getWordsLen() {
        return WordsLen;
    }

    public void setWordsLen(String wordsLen) {
        WordsLen = wordsLen;
    }

    public String getInnerId() {
        return InnerId;
    }

    public void setInnerId(String innerId) {
        InnerId = innerId;
    }

    public boolean isReviewWords() {
        return ReviewWords;
    }

    public void setReviewWords(boolean reviewWords) {
        ReviewWords = reviewWords;
    }

    @Override
    public String toString() {
        return "Case{" +
                "Error='" + Error + '\'' +
                ", Tips='" + Tips + '\'' +
                ", Sentence='" + Sentence + '\'' +
                ", ErrorInfo='" + ErrorInfo + '\'' +
                ", MarkType='" + MarkType + '\'' +
                ", ErrLevel='" + ErrLevel + '\'' +
                ", WordsLen='" + WordsLen + '\'' +
                ", InnerId='" + InnerId + '\'' +
                ", Pos='" + Pos + '\'' +
                ", ReviewWords=" + ReviewWords +
                '}';
    }
}
