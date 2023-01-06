package top.api.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.nio.cs.ext.MS874;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Msg {
    private Integer code;
    private String msg;
    private Object data;

    public static Msg success(String msg){
        Msg _msg = new Msg();
        _msg.code = Code.CODE_OK;
        _msg.msg = msg;
        return _msg;
    }

    public static Msg success(String msg,Object data){
        Msg _msg = new Msg();
        _msg.code = Code.CODE_OK;
        _msg.msg = msg;
        _msg.data = data;
        return _msg;
    }

    public static Msg error(String msg){
        Msg _msg = new Msg();
        _msg.code = Code.CODE_NO;
        _msg.msg = msg;
        return _msg;
    }

    public static Msg error(String msg,Object data){
        Msg _msg = new Msg();
        _msg.code = Code.CODE_NO;
        _msg.msg = msg;
        _msg.data = data;
        return _msg;
    }
}
