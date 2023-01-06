package top.api.interceptor;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import top.api.utils.Code;
import top.api.utils.MD5Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

@Slf4j
@Component
public class SignInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // sign算法  2022年10月09日 + /user/login
        String uri = request.getRequestURI();

        String header = request.getHeader("sign");

        if (header == null || "".equals(header)){
            response.setContentType("application/json; charset=utf-8");
            JSONObject json = new JSONObject();
            json.put("code",Code.CODE_ERR);
            json.put("msg","sign is null");
            PrintWriter writer = response.getWriter();
            writer.append(json.toString());
            log.error("sign is null");
            return false;
            //throw new ControllerException(Code.CODE_ERR,"sign is null");
        }

        String date = new SimpleDateFormat("yyyy年MM月dd日HH时").format(System.currentTimeMillis());

        String sign = MD5Utils.stringToMD5(date + uri);

        System.out.println(sign);

        if (! sign.equals(header)){
            response.setContentType("application/json; charset=utf-8");
            JSONObject json = new JSONObject();
            json.put("code",Code.CODE_ERR);
            json.put("msg","sign is err");
            PrintWriter writer = response.getWriter();
            writer.append(json.toString());
            log.error("sign is err");
            return false;
            //throw new ControllerException(Code.CODE_ERR,"sign is err");
        }

        return true;
    }
}
