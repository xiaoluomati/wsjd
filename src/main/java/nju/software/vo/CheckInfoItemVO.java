package nju.software.vo;

/**
 * Created by away on 2018/3/28.
 */
public class CheckInfoItemVO {

    private ErrorType errorType;
    private String error;
    private String errorTypeName;
    private String tip;

    public CheckInfoItemVO(ErrorType errorType, String error) {
        this.error = error;
        this.tip = "";
        setErrorType(errorType);
    }

    public CheckInfoItemVO(ErrorType errorType, String error, String tip) {
        this.error = error;
        this.tip = tip;
        setErrorType(errorType);
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
        this.errorTypeName = errorType.getName();
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorTypeName() {
        return errorTypeName;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    @Override
    public String toString() {
        return "CheckInfoItemVO{" +
                "errorType=" + errorType +
                ", error='" + error + '\'' +
                ", errorTypeName='" + errorTypeName + '\'' +
                ", tip='" + tip + '\'' +
                '}';
    }
}
