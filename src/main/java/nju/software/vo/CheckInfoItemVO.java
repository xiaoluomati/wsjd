package nju.software.vo;

/**
 * Created by away on 2018/3/28.
 */
public class CheckInfoItemVO {

    private ErrorType errorType;
    private String error;
    private String errorTypeName;

    public CheckInfoItemVO(ErrorType errorType, String error) {
        this.error = error;
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

    @Override
    public String toString() {
        return "CheckInfoItemVO{" +
                "errorType=" + errorType +
                ", error='" + error + '\'' +
                '}';
    }
}
