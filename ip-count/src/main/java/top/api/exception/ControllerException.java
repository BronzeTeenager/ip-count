package top.api.exception;

// 业务异常类
public class ControllerException extends RuntimeException{

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ControllerException(int code, String message) {
        super(message);
        this.code = code;
    }

    public ControllerException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

}
