package top.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.api.interceptor.IpInterceptor;
import top.api.interceptor.SignInterceptor;
import top.api.interceptor.TokenInterceptor;


@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Autowired
    private SignInterceptor signInterceptor;
    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Autowired
    private IpInterceptor ipInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        /**
         * 注册自己的拦截器,并设置拦截的请求路径
         * addPathPatterns为拦截此请求路径的请求
         * excludePathPatterns为不拦截此路径的请求
         */

       // registry.addInterceptor(signInterceptor).addPathPatterns("/**").excludePathPatterns("/utils/checkCode","/ip/ipCount");

        registry.addInterceptor(tokenInterceptor).addPathPatterns("/app/**","/ip/**","/user/userInfo","/utils/signIn").excludePathPatterns("/ip/ipCount");

        registry.addInterceptor(ipInterceptor).addPathPatterns("/**");
    }
}
