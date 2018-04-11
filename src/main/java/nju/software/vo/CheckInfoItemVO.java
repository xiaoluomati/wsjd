package nju.software.vo;

/**
 * Created by away on 2018/3/28.
 */
public class CheckInfoItemVO {

    private ErrorType errorType;
    private String error;
    private String errorTypeName;
    private String tip;
    private ErrorLevelEnum errorLevel;

    public CheckInfoItemVO(ErrorType errorType, String error) {
        this.error = error;
        this.tip = "";
        setErrorType(errorType);
        errorLevel = ErrorLevelEnum.LV_2;
    }

    public CheckInfoItemVO(ErrorType errorType, String error, String tip) {
        this.error = error;
        this.tip = tip;
        setErrorType(errorType);
        errorLevel = ErrorLevelEnum.LV_2;
    }

    public CheckInfoItemVO(ErrorType errorType, String error, String tip, ErrorLevelEnum errorLevel) {
        this.error = error;
        this.tip = tip;
        setErrorType(errorType);
        this.errorLevel = errorLevel;
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

    public ErrorLevelEnum getErrorLevel() {
        return errorLevel;
    }

    public void setErrorLevel(ErrorLevelEnum errorLevel) {
        this.errorLevel = errorLevel;
    }

    @Override
    public String toString() {
        return "CheckInfoItemVO{" +
                "errorType=" + errorType +
                ", error='" + error + '\'' +
                ", errorTypeName='" + errorTypeName + '\'' +
                ", tip='" + tip + '\'' +
                ", errorLevel=" + errorLevel +
                '}';
    }
}
