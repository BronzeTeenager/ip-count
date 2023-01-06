package top.api.interceptor;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import top.api.utils.Code;
import top.api.utils.RSAUtils;
import top.api.utils.TokenUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("token");

        if (! TokenUtils.TokenCheck(token)){
            response.setContentType("application/json; charset=utf-8");
            JSONObject json = new JSONObject();
            json.put("code", Code.CODE_ERR);
            json.put("msg","token is err");
            PrintWriter writer = response.getWriter();
            writer.append(json.toString());
            log.error("token is err");
            return false;
        }

        String tokenStr = TokenUtils.getTokenStr(token);
        JSONObject json = JSONObject.parseObject(tokenStr);

      //  System.out.println("存入id: " + json.get("id"));
        // 将id保存到request域中
        request.setAttribute("id",json.get("id"));

        return true;
    }
}
