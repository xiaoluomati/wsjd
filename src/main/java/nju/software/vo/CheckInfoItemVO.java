package nju.software.vo;

/**
 * Created by away on 2018/3/28.
 */
public class CheckInfoItemVO {

    private ErrorType errorType;
    private String error;

    public CheckInfoItemVO(ErrorType errorType, String error) {
        this.errorType = errorType;
        this.error = error;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "CheckInfoItemVO{" +
                "errorType=" + errorType +
                ", error='" + error + '\'' +
                '}';
    }
}
